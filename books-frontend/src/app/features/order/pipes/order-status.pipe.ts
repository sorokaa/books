import {Pipe, PipeTransform} from "@angular/core";

@Pipe({
  name: "orderStatusPipe"
})
export class OrderStatusPipe implements PipeTransform {

  public transform(status: string | undefined): string {
    if (status === undefined) {
      return ""
    }
    return status.substring(0, 1) + status.substring(1).toLowerCase()
  }
}
