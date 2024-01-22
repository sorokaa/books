import {BookListComponent} from "./book-list.component";
import {NgModule} from '@angular/core';
import {BookModule} from "../book/book.module";
import {BookService} from "../../services/book.service";
import {HttpClientModule} from "@angular/common/http"
import {NgForOf} from "@angular/common";
import {BookFilterModule} from "../book-filter/book-filter.module";
import {MatPaginatorModule} from '@angular/material/paginator';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {BookListService} from "../../services/book-list.service";

@NgModule({
  declarations: [
    BookListComponent
  ],
  imports: [
    BookModule,
    HttpClientModule,
    NgForOf,
    BookFilterModule,
    MatPaginatorModule,
    BrowserAnimationsModule
  ],
  providers: [
    BookService,
    BookListService
  ],
  exports: [
    BookListComponent
  ]
})
export class BookListModule {

}
