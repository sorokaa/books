import {NgModule} from '@angular/core';
import {BookDetailsComponent} from "./book-details.component";
import {NgForOf} from "@angular/common";
import {DictionaryService} from "../../services/dictionary.service";
import {BookStatusPipe} from "../../pipes/book-status.pipe";
import {FileService} from "../../services/file.service";

@NgModule({
  declarations: [
    BookDetailsComponent,
    BookStatusPipe
  ],
  imports: [
    NgForOf
  ],
  providers: [
    DictionaryService,
    FileService
  ],
  exports: [
    BookDetailsComponent
  ]
})
export class BookDetailsModule {

}
