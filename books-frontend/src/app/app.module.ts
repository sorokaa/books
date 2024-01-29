import {APP_INITIALIZER, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BookListModule} from "./features/book/components/book-list/book-list.module";
import {KeycloakAngularModule, KeycloakService} from "keycloak-angular";
import {initializeKeycloak} from "./core/auth/keycloak-init.factory";
import {BookDetailsModule} from "./features/book/components/book-details/book-details.module";
import {SharedModule} from "./features/shared/shared.module";
import {OrderModule} from "./features/order/order.module";
import {CreateBookModule} from "./features/book/components/create-book/create-book.module";
import {CommonModule} from "@angular/common";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    KeycloakAngularModule,
    BookListModule,
    BookDetailsModule,
    AppRoutingModule,
    SharedModule,
    OrderModule,
    CreateBookModule
  ],
  providers: [
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      multi: true,
      deps: [KeycloakService],
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
