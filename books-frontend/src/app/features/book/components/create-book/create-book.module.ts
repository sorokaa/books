import {NgModule} from "@angular/core";
import {CreateBookComponent} from "./create-book.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CommonModule, NgOptimizedImage} from "@angular/common";
import {BookDetailsModule} from "../book-details/book-details.module";

@NgModule({
  declarations: [
    CreateBookComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    NgOptimizedImage,
    BookDetailsModule
  ],
  exports: [CreateBookComponent]
})
export class CreateBookModule {

}
