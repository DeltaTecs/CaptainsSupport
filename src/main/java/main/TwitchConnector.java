package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.philippheuer.credentialmanager.CredentialManager;
import com.github.philippheuer.credentialmanager.CredentialManagerBuilder;
import com.github.philippheuer.credentialmanager.authcontroller.GUIAuthController;
import com.github.philippheuer.credentialmanager.domain.AuthenticationController;
import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.philippheuer.credentialmanager.identityprovider.OAuth2IdentityProvider;
import com.github.philippheuer.events4j.reactor.ReactorEventHandler;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.auth.domain.TwitchScopes;
import com.github.twitch4j.auth.providers.TwitchIdentityProvider;
import com.github.twitch4j.chat.events.channel.ChannelJoinEvent;
import com.github.twitch4j.chat.events.channel.FollowEvent;
import com.github.twitch4j.chat.events.channel.RaidEvent;
import com.github.twitch4j.helix.domain.FollowList;
import com.github.twitch4j.helix.domain.InboundFollow;
import com.github.twitch4j.helix.domain.InboundFollowers;
import com.github.twitch4j.pubsub.events.ChannelPointsRedemptionEvent;
import com.github.twitch4j.pubsub.events.RewardRedeemedEvent;
import features.*;
import features.rewards.RewardHandler;
import io.MailSender;
import io.SoundManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class TwitchConnector {

    private static final Logger LOGGER = LogManager.getLogger(TwitchConnector.class.getName());

    public static boolean oauthFinished = false;

    private static TwitchConnector instance;

    /**
     * Holds the main.Bot main.Configuration
     */
    private static Configuration configuration;

    /**
     * Twitch4J API
     */
    private TwitchClient twitchClient;

    /**
     * Constructor
     */
    public TwitchConnector() {

        LOGGER.info("Starting Twitch Connection");

        // Load main.Configuration
        try {
            loadConfiguration();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        TwitchClientBuilder clientBuilder = TwitchClientBuilder.builder();

        final String clientId = configuration.getApi().get("twitch_client_id");
        final String clientSecret = configuration.getApi().get("twitch_client_secret");
        final OAuth2Credential ircCredential = new OAuth2Credential("twitch", configuration.getCredentials().get("irc"));
        LOGGER.info("Acquiring OAuth token");

        CredentialManager credentialManager = acquireCredentials(clientId, clientSecret); // blocks till webpage opened

        // block until credential recieved. Denial doesnt count
        while (credentialManager.getCredentials().isEmpty()) {
            try {
                Thread.sleep( 20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        oauthFinished = true;

        final OAuth2Credential oAuth2Credential = credentialManager.getOAuth2CredentialByUserId(credentialManager.getCredentials().get(0).getUserId()).get();

        twitchClient = clientBuilder
                .withClientId(clientId)
                .withClientSecret(clientSecret)
                .withCredentialManager(credentialManager)
                .withDefaultAuthToken(oAuth2Credential)
                .withEnableHelix(true)
                .withDefaultEventHandler(ReactorEventHandler.class)
                /*
                 * Chat Module
                 * Joins irc and triggers all chat based events (viewer join/leave/sub/bits/gifted subs/...)
                 */
                .withChatAccount(ircCredential)
                .withEnableChat(true)
                .withEnablePubSub(true)
                .withEnableTMI(true)
                .build();


        // test if bot works
        LOGGER.info("Twitch client authenticated");
        twitchClient.getChat().sendMessage("DeltaTecs", "/me reports for duty");

        InboundFollowers resultList = twitchClient.getHelix().getChannelFollowers(oAuth2Credential.getAccessToken(), "434471444", null, 100, null).execute();
        System.out.println("followers found: " + resultList.getTotal().intValue());
        for (InboundFollow f : resultList.getFollows()) {
            System.out.println(f.getUserName() + " id " + f.getUserId() + " follows deltatecs since " + f.getFollowedAt().toString());
        }
        System.out.println("---- dahek");

        connect(oAuth2Credential);

        instance = this;
    }

    /**
     * Method to register all features
     */
    public void registerFeatures() {
        ReactorEventHandler eventHandler = twitchClient.getEventManager().getEventHandler(ReactorEventHandler.class);

        LOGGER.info("Registering Twitch features");

        eventHandler.onEvent(ChannelPointsRedemptionEvent.class, RewardHandler::handle);
        eventHandler.onEvent(FollowEvent.class, FollowHandler::handle);
        eventHandler.onEvent(RaidEvent.class, RaidHandler::handle);
    }

    /**
     * Load the main.Configuration
     */
    private void loadConfiguration() throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("config.yaml");

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        configuration = mapper.readValue(is, Configuration.class);
    }

    /**
     * Connects/subcribes to selected twitch services
     * @param credential oauth token obtained with wide permissions
     */
    public void connect(OAuth2Credential credential) {
        // Connect and subscribe to selected services
        twitchClient.getPubSub().listenForChannelPointsRedemptionEvents(credential, configuration.getChanelId());
        twitchClient.getPubSub().listenForFollowingEvents(credential, configuration.getChanelId());
        twitchClient.getPubSub().listenForRaidEvents(credential, configuration.getChanelId());
        twitchClient.getClientHelper().enableStreamEventListener(configuration.getChannel());
        twitchClient.getClientHelper().enableFollowEventListener(configuration.getChannel());
    }

    /**
     * Starts oauth flow
     * @param cliendId
     * @param clientSecret
     * @return
     */
    private CredentialManager acquireCredentials(String cliendId, String clientSecret) {

        // build
        GUIAuthController guiAuthController = new GUIAuthController();
        CredentialManager credentialManager = CredentialManagerBuilder.builder()
                .withAuthenticationController(guiAuthController)
                .build();
        // register idp
        TwitchIdentityProvider twitchIdentityProvider = new TwitchIdentityProvider(cliendId, clientSecret, "http://localhost:31921/casimir_ist_der_beste");
        credentialManager.registerIdentityProvider(twitchIdentityProvider);
        // start oauth2 flow
        credentialManager.getAuthenticationController().startOAuth2AuthorizationCodeGrantType(twitchIdentityProvider, "http://localhost:31921/casimir_ist_der_beste", Arrays.asList(
                TwitchScopes.CHAT_READ, TwitchScopes.CHAT_EDIT, TwitchScopes.CHAT_WHISPERS_EDIT, TwitchScopes.CHAT_WHISPERS_READ, TwitchScopes.CHAT_CHANNEL_MODERATE, TwitchScopes.HELIX_USER_FOLLOWS_READ, TwitchScopes.HELIX_ANALYTICS_READ_EXTENSIONS, TwitchScopes.HELIX_CHANNEL_EXTENSION_MANAGE, TwitchScopes.HELIX_CHANNEL_GOALS_READ, TwitchScopes.HELIX_CHANNEL_HYPE_TRAIN_READ, TwitchScopes.HELIX_CHANNEL_POLLS_MANAGE, TwitchScopes.HELIX_CHANNEL_POLLS_READ, TwitchScopes.HELIX_CHANNEL_PREDICTIONS_MANAGE, TwitchScopes.HELIX_CHANNEL_REDEMPTIONS_READ, TwitchScopes.HELIX_BITS_READ, TwitchScopes.HELIX_CHANNEL_SUBSCRIPTIONS_READ, TwitchScopes.HELIX_CLIPS_EDIT, TwitchScopes.HELIX_CHANNEL_VIDEOS_MANAGE, TwitchScopes.HELIX_USER_EDIT_BROADCAST, TwitchScopes.HELIX_USER_EDIT_FOLLOWS, "channel:manage:redemptions"
        ));
        return credentialManager;
    }

    public TwitchClient getTwitchClient() {
        return twitchClient;
    }

    public static Configuration getConfiguration() {
        return configuration;
    }

    public static TwitchClient client() {
        return instance.getTwitchClient();
    }

    public static void chat(String message) {
        client().getChat().sendMessage(configuration.getChannel(), message);
    }


}
