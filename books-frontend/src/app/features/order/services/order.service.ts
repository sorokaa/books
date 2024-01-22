import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Order} from "../models/order.model";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class OrderService {

  baseUri: string = "http://localhost:8280"

  constructor(private http: HttpClient) {
  }

  getCurrentUserOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(`${this.baseUri}/api/orders/current`)
  }
}
