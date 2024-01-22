import {Injectable} from "@angular/core";
import {BookFilter} from "../models/filter/book-filter.model";
import {BehaviorSubject, Observable} from "rxjs";

@Injectable()
export class BookListService {

  bookFilterSubject = new BehaviorSubject<any>({
    name: null,
    priceRange: {minValue: null, maxValue: null},
    yearRange: {minValue: null, maxValue: null},
    pagesRange: {minValue: null, maxValue: null}
  });

  pageSize: number = 10
  currentPage: number = 0

  getFilterObservable(): Observable<BookFilter> {
    return this.bookFilterSubject.asObservable();
  }

  updateFilter(filter: BookFilter) {
    this.currentPage = 0
    this.bookFilterSubject.next(filter)
  }

  loadMore(): void {
    this.currentPage++
    this.bookFilterSubject.next(this.bookFilterSubject.value)
  }

  updatePageSize(size: number) {
    this.pageSize = size
    this.currentPage = 0
    this.bookFilterSubject.next(this.bookFilterSubject.value)
  }
}
