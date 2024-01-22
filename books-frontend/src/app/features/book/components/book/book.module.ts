import {NgModule} from '@angular/core';
import {BookComponent} from "./book.component";
import {RouterLink} from "@angular/router";
import {NgOptimizedImage} from "@angular/common";
import {FileService} from "../../services/file.service";

@NgModule({
  declarations: [
    BookComponent
  ],
  imports: [
    RouterLink,
    NgOptimizedImage
  ],
  providers: [
    FileService
  ],
  exports: [
    BookComponent
  ]
})
export class BookModule {

}
