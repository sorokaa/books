import {Component, Input, OnInit} from "@angular/core";
import {Order} from "../../models/order.model";
import {OrderService} from "../../services/order.service";

@Component({
  selector: 'order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit {

  orders: Order[] = []

  constructor(private orderService: OrderService) {
  }

  ngOnInit() {
    this.fetchCurrentOrders()
  }

  private fetchCurrentOrders() {
    this.orderService.getCurrentUserOrders().subscribe(
      next => this.orders = next
    )
  }
}
