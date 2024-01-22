import {Component} from "@angular/core";
import {KeycloakService} from "keycloak-angular";

@Component({
  selector: "app-header",
  templateUrl: "./header.component.html",
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  constructor(private keycloakService: KeycloakService) {
  }

  onLogout() {
    this.keycloakService.logout('http://localhost:4200').then(
      r => this.keycloakService.clearToken()
    )
  }
}
