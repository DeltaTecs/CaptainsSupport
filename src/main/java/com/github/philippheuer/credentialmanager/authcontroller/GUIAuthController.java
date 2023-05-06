package com.github.philippheuer.credentialmanager.authcontroller;

import com.github.philippheuer.credentialmanager.domain.AuthenticationController;
import com.github.philippheuer.credentialmanager.identityprovider.OAuth2IdentityProvider;
import com.github.philippheuer.credentialmanager.util.WebsiteUtils;
import com.github.philippheuer.credentialmanager.webserver.WebServer;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Auth Controller to use in GUI Applications
 * <p>
 * Will start a temporary local webserver to get the result.
 */
@Slf4j
public class GUIAuthController extends AuthenticationController {

    /**
     * Holds the WebServer
     */
    private final WebServer webServer = new WebServer();

    /**
     * Starts the OAuth2Flow for the specified OAuth2 Identity Provider
     * <p>
     * Like the Authorization Code Grant Type, the Implicit Grant starts out by building a link and directing the user’s browser to that URL. At a high level, the flow has the following steps
     *  The application opens a browser to send the user to the OAuth server
     *  The user sees the authorization prompt and approves the app’s request
     *  The user is redirected back to the application with an access token in the URL fragment
     *
     * @param oAuth2IdentityProvider OAuth2 Identity Provider
     * @param overwriteRedirectUrl Redirect url
     * @param scopes Requested scopes
     */
    public void startOAuth2AuthorizationCodeGrantType(OAuth2IdentityProvider oAuth2IdentityProvider, String overwriteRedirectUrl, List<Object> scopes) {
        // generate auth url
        String authUrl = oAuth2IdentityProvider.getAuthenticationUrl(overwriteRedirectUrl, scopes, null);

        // start integrated webserver
        webServer.setAuthenticationController(this);
        webServer.startAuthListener();

        // open website
        WebsiteUtils.openWebpage(authUrl);
    }

}
