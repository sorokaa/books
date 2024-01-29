import {Injectable} from "@angular/core";
import {environments} from "../../../../environments/environments";
import {HttpClient, HttpEvent, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable()
export class FileService {

  private baseUrl: string = environments.fileServerUri

  constructor(private http: HttpClient) {
  }

  public getFileUrl(id: number | undefined): string {
    return `${this.baseUrl}/api/files/${id}`
  }

  public uploadImage(image: File): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();
    formData.append("file", image)
    const request = new HttpRequest(
      'POST',
      `${this.baseUrl}/api/files`,
      formData,
      {
        reportProgress: true,
        responseType: 'json',
      }
    )
    return this.http.request(request);
  }

  public deleteById(id: number) {
    this.http.delete(`${this.baseUrl}/api/files/${id}`).subscribe(

    )
  }
}
