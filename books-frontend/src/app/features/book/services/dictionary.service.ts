import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Language} from "../models/language.model";
import {Category} from "../models/category.model";
import {environments} from "../../../../environments/environments";

@Injectable({
  providedIn: 'root'
})
export class DictionaryService {

  private baseUri = environments.dictionaryServerUri

  constructor(private http: HttpClient) {
  }

  getLanguageById(id: number): Observable<Language> {
    return this.http.get<Language>(`${this.baseUri}/api/dictionaries/languages/${id}`)
  }

  getCategoriesByIds(ids: number[]): Observable<Category[]> {
    return this.http.get<Category[]>(`${this.baseUri}/api/dictionaries/categories/by-ids?ids=${ids}`)
  }

  getAllLanguages(name: string | null): Observable<Language[]> {
    let url = `${this.baseUri}/api/dictionaries/languages?limit=5`;
    if (name !== null) {
      url += `&name=${name}`
    }
    return this.http.get<Language[]>(url)
  }

  getAllCategories(name: string | null): Observable<Category[]> {
    let url = `${this.baseUri}/api/dictionaries/categories?limit=5`;
    if (name !== null) {
      url += `&name=${name}`
    }
    return this.http.get<Category[]>(url)
  }
}
