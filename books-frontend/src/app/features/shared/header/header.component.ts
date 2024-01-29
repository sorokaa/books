import {Component} from "@angular/core";
import {AuthGuard} from "../../../core/auth/auth.guard";

@Component({
  selector: "app-header",
  templateUrl: "./header.component.html",
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  constructor(private authGuard: AuthGuard) {

  }

  public onLogout(): void {
    this.authGuard.logout();
  }

  public hasRole(role: string): boolean {
    return this.authGuard.hasRole(role);
  }
}
