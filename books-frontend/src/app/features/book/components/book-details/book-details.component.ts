import {Component} from "@angular/core";
import {Book} from "../../models/book.model";
import {BookService} from "../../services/book.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Category} from "../../models/category.model";
import {Language} from "../../models/language.model";
import {DictionaryService} from "../../services/dictionary.service";
import {FileService} from "../../services/file.service";
import {OrderService} from "../../../order/services/order.service";
import {AuthGuard} from "../../../../core/auth/auth.guard";
import {BookStatus} from "../../models/book-status.model";

@Component({
  selector: 'book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})
export class BookDetailsComponent {

  book: Book | undefined
  language: Language | undefined
  categories: Category[] | undefined
  isAdminUser: boolean = false

  constructor(private bookService: BookService,
              private route: ActivatedRoute,
              private dictionaryService: DictionaryService,
              private fileService: FileService,
              private orderService: OrderService,
              private authGuard: AuthGuard,
              private router: Router) {
    this.route.params.subscribe(
      params => this.initBook(params['id'])
    )
    this.isAdminUser = this.authGuard.hasRole('ROLE_ADMIN')
  }

  public getFileUrl(): string {
    return this.fileService.getFileUrl(this.book?.pictureId)
  }

  public createOrder(bookId: number): void {
    this.orderService.createOrder(bookId)
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

  public isNotAvailableToOrder(): boolean {
    return this.book?.status === BookStatus.SOLD_OUT
  }

  public deleteBook(id: number): void {
    this.bookService.deleteBook(id).subscribe(
      next => this.router.navigate(['/books'])
    )
  }
}
