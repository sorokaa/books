import {Component, OnInit} from "@angular/core";
import {BookService} from "../../services/book.service";
import {BookShort} from "../../models/book-short.model";
import {Page} from "../../models/page.model";
import {BookListService} from "../../services/book-list.service";

@Component({
  selector: 'book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {

  books: Page<BookShort> | undefined
  booksList: BookShort[] | undefined = []

  constructor(private bookService: BookService,
              private bookListService: BookListService) {

  }

  ngOnInit() {
    this.fetchBooks()
  }

  public fetchBooks(): void {
    this.bookListService.getFilterObservable().subscribe(
      filter => {
        this.bookService.getByFilter(filter, this.bookListService.currentPage, this.bookListService.pageSize).subscribe(
          next => {
            this.books = next
            if (this.bookListService.currentPage == 0) {
              this.booksList = []
            }
            this.booksList = this.booksList?.concat(next.content)
          }
        );
      }
    )
  }

  loadMore() {
    if (this.books?.last) {
      return
    }
    this.bookListService.loadMore()
  }

  getPageSize() {
    return this.bookListService.pageSize
  }

  selectPageSize(size: number) {
    const currentPageSize = this.bookListService.pageSize;
    if (size === currentPageSize) {
      return
    }
    this.bookListService.updatePageSize(size)
  }
}
