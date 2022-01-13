import OktaSignIn from '@okta/okta-signin-widget'
import { OktaAuth } from '@okta/okta-auth-js'

const yourOktaUri = 'https://dev-9716109.okta.com';
const clientId = '0oa3l5m9dmqKFoUDe5d7';

const oktaSignIn = new OktaSignIn({
    baseUrl: yourOktaUri,
    clientId: clientId,
    redirectUri: 'http://localhost:8081/login/callback',
    authParams: {
        pkce: true,
        issuer: `${yourOktaUri}/oauth2/default`,
        display: 'page',
        scopes: ['openid', 'profile', 'email']
    }
});

const oktaAuth = new OktaAuth({
    issuer: `${yourOktaUri}/oauth2/default`,
    clientId: clientId,
    redirectUri: window.location.origin + '/login/callback',
    scopes: ['openid', 'profile', 'email']
})

export { oktaAuth, oktaSignIn };