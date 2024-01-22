import {Component, Input, OnInit} from "@angular/core";
import {Order} from "../../models/order.model";
import {BookService} from "../../../book/services/book.service";
import {BookShort} from "../../../book/models/book-short.model";

@Component({
  selector: 'order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  @Input()
  order: Order | undefined
  book: BookShort | undefined

  constructor(private booksService: BookService) {
  }

  ngOnInit() {
    if (this.order === undefined) {
      return
    }
    this.booksService.getBookById(this.order?.bookId).subscribe(
      next => this.book = next
    )
  }
}
