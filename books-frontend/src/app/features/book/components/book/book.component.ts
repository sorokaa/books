import {Component, Input} from "@angular/core";
import {BookShort} from "../../models/book-short.model";
import {FileService} from "../../services/file.service";

@Component({
  selector: 'book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent {

  @Input()
  book: BookShort | undefined

  constructor(private fileService: FileService) {
  }

  getFileUrl() {
    return this.fileService.getFileUrl(this.book?.pictureId)
  }
}
