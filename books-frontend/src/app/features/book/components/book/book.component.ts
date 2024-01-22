import {Book} from "../../models/book.model";
import {Component, Input, OnInit} from "@angular/core";
import {BookShort} from "../../models/book-short.model";

@Component({
  selector: 'book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

  @Input()
  book: BookShort | undefined

  ngOnInit() {

  }
}
