import {NgModule} from '@angular/core';
import {BookComponent} from "./book.component";
import {RouterLink} from "@angular/router";
import {NgOptimizedImage} from "@angular/common";

@NgModule({
    declarations: [
        BookComponent
    ],
  imports: [
    RouterLink,
    NgOptimizedImage
  ],
    exports: [
        BookComponent
    ]
})
export class BookModule {

}
