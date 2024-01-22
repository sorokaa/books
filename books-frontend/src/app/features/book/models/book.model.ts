import {Author} from "./author.model";
import {BookShort} from "./book-short.model";
import {Publisher} from "./publisher.model";

export interface Book extends BookShort {
  authors: Author[],
  publisher: Publisher,
  languageId: number,
  year: number,
  pages: number,
  categoryIds: number[],
  description: string
}
