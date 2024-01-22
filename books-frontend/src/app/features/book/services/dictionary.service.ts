import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Language} from "../models/language.model";
import {Category} from "../models/category.model";

@Injectable({
  providedIn: 'root'
})
export class DictionaryService {

  private baseUri: string = "http://localhost:8180"

  constructor(private http: HttpClient) {
  }

  getLanguageById(id: number): Observable<Language> {
    return this.http.get<Language>(`${this.baseUri}/api/dictionaries/languages/${id}`)
  }

  getCategoriesByIds(ids: number[]): Observable<Category[]> {
    return this.http.get<Category[]>(`${this.baseUri}/api/dictionaries/categories/by-ids?ids=${ids}`)
  }

  getAllLanguages(): Observable<Language[]> {
    return this.http.get<Language[]>(`${this.baseUri}/api/dictionaries/languages`)
  }

  getAllCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(`${this.baseUri}/api/dictionaries/categories`)
  }
}
