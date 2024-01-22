import {NgModule} from "@angular/core";
import {OrderComponent} from "./components/order/order.component";
import {OrderListComponent} from "./components/order-list/order-list.component";
import {OrderService} from "./services/order.service";
import {DatePipe, NgForOf} from "@angular/common";
import {OrderStatusPipe} from "./pipes/order-status.pipe";
import {RouterLink} from "@angular/router";

@NgModule({
  declarations: [
    OrderComponent,
    OrderListComponent,
    OrderStatusPipe
  ],
  providers: [
    OrderService
  ],
  imports: [
    NgForOf,
    DatePipe,
    RouterLink
  ],
  exports: [
    OrderComponent,
    OrderListComponent
  ]
})
export class OrderModule {

}
