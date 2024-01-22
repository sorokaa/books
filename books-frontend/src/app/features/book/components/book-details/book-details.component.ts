import {Component} from "@angular/core";
import {Book} from "../../models/book.model";
import {BookService} from "../../services/book.service";
import {ActivatedRoute} from "@angular/router";
import {Category} from "../../models/category.model";
import {Language} from "../../models/language.model";
import {DictionaryService} from "../../services/dictionary.service";
import {FileService} from "../../services/file.service";

@Component({
  selector: 'book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})
export class BookDetailsComponent {

  book: Book | undefined
  language: Language | undefined
  categories: Category[] | undefined

  constructor(private bookService: BookService,
              private route: ActivatedRoute,
              private dictionaryService: DictionaryService,
              private fileService: FileService) {
    this.route.params.subscribe(
      params => this.initBook(params['id'])
    )
  }

  private initBook(id: number): void {
    this.bookService.getBookById(id).subscribe(
      next => {
        this.book = next
        this.fillDictionaries(this.book)
      }
    )
  }

  private fillDictionaries(book: Book) {
    this.dictionaryService.getLanguageById(book.languageId).subscribe(
      next => this.language = next
    )
    this.dictionaryService.getCategoriesByIds(book.categoryIds).subscribe(
      next => this.categories = next
    )
  }

  getFileUrl() {
    return this.fileService.getFileUrl(this.book?.pictureId)
  }
}
