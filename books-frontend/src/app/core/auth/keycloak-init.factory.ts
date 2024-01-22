import {KeycloakService} from "keycloak-angular";
import {environments} from "../../../environments/environments";

export function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
    keycloak.init({
      config: {
        url: environments.keycloakHost,
        realm: 'books',
        clientId: 'books-client',
      },
      initOptions: {
        onLoad: 'login-required',
        flow: "standard"
      },
    });
}
