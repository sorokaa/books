import {NgModule} from '@angular/core';
import {BookDetailsComponent} from "./book-details.component";
import {CommonModule} from "@angular/common";
import {DictionaryService} from "../../services/dictionary.service";
import {BookStatusPipe} from "../../pipes/book-status.pipe";
import {FileService} from "../../services/file.service";
import {AuthorsPipe} from "../../pipes/authors.pipe";
import {AuthGuard} from "../../../../core/auth/auth.guard";

@NgModule({
  declarations: [
    BookDetailsComponent,
    BookStatusPipe,
    AuthorsPipe
  ],
  imports: [
    CommonModule
  ],
  providers: [
    DictionaryService,
    FileService,
    AuthGuard
  ],
  exports: [
    BookDetailsComponent,
    BookStatusPipe
  ]
})
export class BookDetailsModule {

}
