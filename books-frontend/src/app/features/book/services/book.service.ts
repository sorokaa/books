import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {BookShort} from "../models/book-short.model";
import {Book} from "../models/book.model";
import {Page} from "../models/page.model";
import {BookFilter} from "../models/filter/book-filter.model";
import {Author} from "../models/author.model";
import {Publisher} from "../models/publisher.model";
import {environments} from "../../../../environments/environments";

@Injectable()
export class BookService {

  private baseUri = environments.bookServerUri

  constructor(private http: HttpClient) {

  }

  getByFilter(filter: BookFilter, page: number, size: number): Observable<Page<BookShort>> {
    return this.http.post<Page<BookShort>>(
      `${this.baseUri}/api/books/filter?size=${size}&page=${page}`,
      filter
    )
  }

  getBookById(id: number): Observable<Book> {
    return this.http.get<Book>(`${this.baseUri}/api/books/${id}`);
  }

  getAllAuthors(): Observable<Author[]> {
    return this.http.get<Author[]>(`${this.baseUri}/api/authors`)
  }

  getAllPublishers(): Observable<Publisher[]> {
    return this.http.get<Publisher[]>(`${this.baseUri}/api/publishers`)
  }
}
