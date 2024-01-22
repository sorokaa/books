import {Injectable} from "@angular/core";
import {environments} from "../../../../environments/environments";

@Injectable()
export class FileService {

  private baseUri: string = environments.fileServerUri

  getFileUrl(id: number | undefined) {
    return `${this.baseUri}/api/files/${id}`
  }
}
