package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.philippheuer.events4j.reactor.ReactorEventHandler;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.chat.events.channel.ChannelJoinEvent;
import com.github.twitch4j.pubsub.events.ChannelPointsRedemptionEvent;
import com.github.twitch4j.pubsub.events.RewardRedeemedEvent;
import features.ChannelNotificationOnDonation;
import features.ChannelNotificationOnFollow;
import features.ChannelNotificationOnSubscription;
import features.WriteChannelChatToConsole;
import features.rewards.RewardHandler;

import java.io.InputStream;

public class TwitchConnector {

    /**
     * Holds the main.Bot main.Configuration
     */
    private Configuration configuration;

    /**
     * Twitch4J API
     */
    private TwitchClient twitchClient;

    /**
     * Constructor
     */
    public TwitchConnector() {

        // Load main.Configuration
        loadConfiguration();

        TwitchClientBuilder clientBuilder = TwitchClientBuilder.builder();

        //region Auth
        OAuth2Credential credential = new OAuth2Credential(
                "twitch",
                configuration.getCredentials().get("irc")
        );
        //endregion

        //region TwitchClient
        twitchClient = clientBuilder
                .withClientId(configuration.getApi().get("twitch_client_id"))
                .withClientSecret(configuration.getApi().get("twitch_client_secret"))
                .withEnableHelix(true)
                .withDefaultEventHandler(ReactorEventHandler.class)
                /*
                 * Chat Module
                 * Joins irc and triggers all chat based events (viewer join/leave/sub/bits/gifted subs/...)
                 */
                .withChatAccount(credential)
                .withEnableChat(true)
                .withEnablePubSub(true)
                .withEnableTMI(true)
                /*
                 * GraphQL has a limited support
                 * Don't expect a bunch of features enabling it
                 */
                .withEnableGraphQL(true)
                /*
                 * Kraken is going to be deprecated
                 * see : https://dev.twitch.tv/docs/v5/#which-api-version-can-you-use
                 * It is only here so you can call methods that are not (yet)
                 * implemented in Helix
                 */
                .withEnableKraken(true)
                /*
                 * Build the TwitchClient Instance
                 */
                .build();

        //endregion
    }

    /**
     * Method to register all features
     */
    public void registerFeatures() {
        ReactorEventHandler eventHandler = twitchClient.getEventManager().getEventHandler(ReactorEventHandler.class);

        // Register Event-based features
        /*ChannelNotificationOnDonation channelNotificationOnDonation = new ChannelNotificationOnDonation(eventHandler);
        ChannelNotificationOnFollow channelNotificationOnFollow = new ChannelNotificationOnFollow(eventHandler);
        ChannelNotificationOnSubscription channelNotificationOnSubscription = new ChannelNotificationOnSubscription(eventHandler);
        WriteChannelChatToConsole writeChannelChatToConsole = new WriteChannelChatToConsole(eventHandler);*/

        System.out.println("Registering FEATURTESSSS");

        eventHandler.onEvent(ChannelJoinEvent.class, event -> {
            System.out.printf("%s joined %s\n", event.getUser(), event.getChannel().getName());
        });

        twitchClient.getEventManager().onEvent(ChannelPointsRedemptionEvent.class, event -> {
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            RewardHandler.handle(event);
        });
        //eventHandler.onEvent(RewardEvent, RewardHandler::handle);

    }

    /**
     * Load the main.Configuration
     */
    private void loadConfiguration() {
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream("config.yaml");

            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            configuration = mapper.readValue(is, Configuration.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Unable to load main.Configuration ... Exiting.");
            System.exit(1);
        }
    }

    public void connect() {
        // Connect to all channels
        for (String channel : configuration.getChannels()) {
            twitchClient.getChat().joinChannel(channel);
        }
    }


    /*



                .withClientId(System.getenv("CLIENT_ID"))
                .withClientSecret(System.getenv("CLIENT_SECRET"))
                .withEnableHelix(true)
                .withEnableKraken(true)
                .withEnablePubSub(true)
                .withEnableTMI(true)
                .withEnableV5(true)
                .withEnableChat(true)
                .withEnableClips(true)
                .withEnableUsers(true)
                .withEnableBits(true)
                .withEnableEntitlements(true)
                .withEnableExtensions(true)
                .withEnableHypeTrain(true)
                .withEnableModeration(true)
                .withEnableStreams(true)
                .withEnableSubscriptions(true)
                .withEnableTeams(true)
                .withEnableVideos(true)
                .withEnableWebhooks(true)
                .withEnableWhispers(true)
                .withEnableAnalytics(true)
                .withEnableChatSettings(true)
                .withEnableChannelFeed(true)
                .withEnableChannelPins(true)
                .withEnableChannelPoints(true)
                .withEnableChatModeration(true)
                .withEnableChatRooms(true)
                .withEnableCommunities(true)
                .withEnableCommunityModeration(true)
                .withEnableCommunityStreams(true)
                .withEnableCommunityUsers(true)
                .withEnableFollows(true)
                .withEnableGames(true)
                .withEnableIngests(true)
                .withEnableSearch(true)
                .withEnableStreamMarkers(true)
                .withEnableStreamsMetadata(true)
                .withEnableStreamsTags(true)
                .withEnableStreamsFilter(true)
                .withEnableStreamsCommercials(true)
                .withEnableStreamsTranscoding(true)
                .withEnableStreamsBroadcasterTypes(true)
                .withEnableStreamsType(true)
                .withEnableStreamsLanguage(true)
                .withEnableStreamsUserFollows(true)
                .withEnableStreamsUserFollowsEdit(true)
                .withEnableStreamsUserFollowsDelete(true)
                .withEnableStreamsUserFollowsRead(true)
                .withEnableStreamsUserFollowsWrite(true)
                .withEnableStreamsUserFollowsEditOwn(true)

     */

}
