import {KeycloakService} from "keycloak-angular";

export function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
    keycloak.init({
      config: {
        url: 'http://localhost:18080',
        realm: 'books',
        clientId: 'books-client',
      },
      initOptions: {
        onLoad: 'login-required',
        flow: "standard"
      },
    });
}
