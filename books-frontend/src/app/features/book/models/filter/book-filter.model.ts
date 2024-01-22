import {Range} from "./range.model";

export interface BookFilter {

  name: string | null,
  priceRange: Range | null,
  yearRange: Range | null,
  pagesRange: | Range | null,
  publisherIds: number[] | null,
  categoryIds: number[] | null,
  languageIds: number[] | null,
  authorIds: number[] | null
}
