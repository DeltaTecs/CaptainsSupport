package com.github.philippheuer.credentialmanager.webserver;

import com.github.philippheuer.credentialmanager.domain.AuthenticationController;
import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import main.TwitchConnector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ratpack.core.server.RatpackServer;

import java.util.regex.Pattern;

@Slf4j
public class WebServer {

    private static final Logger LOGGER = LogManager.getLogger(WebServer.class.getName());

    /**
     * Holds the Auth Controller
     */
    @Setter
    private AuthenticationController authenticationController;

    /**
     * Holds the RatPack Server Instance
     */
    private RatpackServer ratpackServer = null;

    /**
     * Server Port
     */
    private final Integer httpPort = 31921;

    /**
     * Starts the Auth Listener
     */
    public void startAuthListener() {
        // make sure old listeners are killed
        stopAuthListener();

        // start listener
        try {
            this.ratpackServer = RatpackServer.start(spec -> spec
                    .serverConfig(s -> s.port(httpPort))
                    .handlers(chain -> chain
                            .get(ctx -> ctx.render("Temporary OAuth2 Server!"))
                            .get("casimir_ist_der_beste", ctx -> {
                                try {
                                    String oAuth2Code = ctx.getRequest().getQueryParams().get("code");
                                    String oAuth2State = ctx.getRequest().getQueryParams().get("state");
                                    LOGGER.debug("Received oauth2 request with code " + oAuth2Code + " and state " + oAuth2State);

                                    if (oAuth2State.contains("|")) {
                                        // contains csrf check & provider name
                                        String providerName = oAuth2State.split(Pattern.quote("|"))[0];
                                        String csrfValue = oAuth2State.split(Pattern.quote("|"))[1];
                                        LOGGER.trace("Provider Name: " + providerName);
                                        LOGGER.trace("CSRF: " + csrfValue);

                                        // add credential
                                        LOGGER.debug("Trying to find OAuth2IdentityProvider by Name [" + providerName + "]");
                                        OAuth2Credential credential = authenticationController.getCredentialManager().getOAuth2IdentityProviderByName(providerName).get().getCredentialByCode(oAuth2Code);
                                        this.authenticationController.getCredentialManager().addCredential(providerName, credential);
                                    }

                                    ctx.render("Authentication successfully!");
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                    ctx.render("Error: " + ex.getMessage());
                                }
                            })
                    )
            );
        } catch (java.net.BindException ex) {
            LOGGER.error(ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Stops the Auth Listener
     */
    @SneakyThrows
    public void stopAuthListener() {
        if (ratpackServer != null) {
            ratpackServer.stop();
            ratpackServer = null;
        }
    }

}
