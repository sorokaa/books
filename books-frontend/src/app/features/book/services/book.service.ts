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

  private baseUrl = environments.bookServerUri

  constructor(private http: HttpClient) {

  }

  public getByFilter(filter: BookFilter, page: number, size: number): Observable<Page<BookShort>> {
    return this.http.post<Page<BookShort>>(
      `${this.baseUrl}/api/books/filter?size=${size}&page=${page}`,
      filter
    )
  }

  public getBookById(id: number): Observable<Book> {
    return this.http.get<Book>(`${this.baseUrl}/api/books/${id}`);
  }

  public getAllAuthors(): Observable<Author[]> {
    return this.http.get<Author[]>(`${this.baseUrl}/api/authors`)
  }

  public getAllPublishers(): Observable<Publisher[]> {
    return this.http.get<Publisher[]>(`${this.baseUrl}/api/publishers`)
  }

  public create(value: any): Observable<Book> {
    return this.http.post<Book>(`${this.baseUrl}/api/books`, value)
  }

  public deleteBook(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/api/books/${id}`)
  }
}
