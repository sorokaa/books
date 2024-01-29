import {BookStatus} from "./book-status.model";

export interface BookShort {
  id: number,
  name: string,
  price: number,
  status: BookStatus,
  pictureId: number
  authorsNames: string[]
}
