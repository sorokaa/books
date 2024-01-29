import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Order} from "../models/order.model";
import {HttpClient} from "@angular/common/http";
import {environments} from "../../../../environments/environments";

@Injectable()
export class OrderService {

  private baseUri: string = environments.orderServerUri

  constructor(private http: HttpClient) {
  }

  getCurrentUserOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(`${this.baseUri}/api/orders/current`)
  }

  createOrder(bookId: number | undefined) {
    this.http.post(`${this.baseUri}/api/orders/${bookId}/order`, null).subscribe(
      next => next
    )
  }
}
