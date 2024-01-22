import {Pipe, PipeTransform} from "@angular/core";

@Pipe({
  name: 'bookStatusPipe'
})
export class BookStatusPipe implements PipeTransform {

  transform(value: string | undefined): string {
    switch (value) {
      case "AVAILABLE":
        return "Available"
      case "PRE_ORDER":
        return "Pre order"
      case "SOLD_OUT":
        return "Sold out"
      default:
        return ""
    }
  }
}
