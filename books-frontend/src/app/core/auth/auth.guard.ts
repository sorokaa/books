import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, Router, RouterStateSnapshot, UrlTree} from '@angular/router';
import {KeycloakAuthGuard, KeycloakService} from 'keycloak-angular';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard extends KeycloakAuthGuard {

  constructor(
    protected override readonly router: Router,
    protected readonly keycloak: KeycloakService
  ) {
    super(router, keycloak);
  }

  async isAccessAllowed(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Promise<boolean | UrlTree> {
    if (!this.authenticated) {
      await this.keycloak.login({
        redirectUri: window.location.origin + state.url
      });
    }
    const requiredRoles = route.data['roles'];
    let permission;
    if (!requiredRoles || requiredRoles.length === 0) {
      permission = true;
    } else {
      if (!this.roles || this.roles.length === 0) {
        permission = false
      }
      permission = !!requiredRoles.every((role: string) => this.roles.indexOf(role) > -1);
    }

    if (!permission) {
      this.router.navigate(['/'])
    }

    return this.authenticated;
  }

  public hasRole(requiredRole: string): boolean {
    return this.keycloak.isUserInRole(requiredRole)
  }

  public logout(): void {
    this.keycloak.logout('http://localhost:4200').then()
  }
}
