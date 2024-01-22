export interface Page<T> {

  content: T[],
  page: number,
  totalElements: number,
  last: boolean,
  totalPages: number
}
