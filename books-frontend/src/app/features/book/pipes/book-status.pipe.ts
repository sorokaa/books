import {Pipe, PipeTransform} from "@angular/core";
import {BookStatus} from "../models/book-status.model";

@Pipe({
  name: 'bookStatusPipe'
})
export class BookStatusPipe implements PipeTransform {

  transform(value: BookStatus | undefined): string {
    if (value === BookStatus.AVAILABLE) {
      return "Available"
    } else if (value == BookStatus.PRE_ORDER) {
      return "Pre order"
    } else {
      return "Sold out"
    }
  }
}
