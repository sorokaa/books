import {Pipe, PipeTransform} from "@angular/core";
import {Author} from "../models/author.model";

@Pipe({
  name: 'authorsPipe'
})
export class AuthorsPipe implements PipeTransform {

  public transform(value: Author[] | undefined): string {
    if (value === undefined || value.length === 0) {
      return "Unknown author"
    }
    return "by " + value.map(author => author.name).join(", ")
  }
}
