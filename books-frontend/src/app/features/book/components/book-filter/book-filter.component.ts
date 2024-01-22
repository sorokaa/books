import {Component, OnInit} from "@angular/core";
import {FormArray, FormBuilder, FormControl} from "@angular/forms";
import {BookService} from "../../services/book.service";
import {BookListService} from "../../services/book-list.service";
import {Publisher} from "../../models/publisher.model";
import {Category} from "../../models/category.model";
import {Author} from "../../models/author.model";
import {DictionaryService} from "../../services/dictionary.service";
import {Language} from "../../models/language.model";

@Component({
  selector: "book-filter",
  templateUrl: './book-filter.component.html',
  styleUrls: ['book-filter.component.css']
})
export class BookFilterComponent implements OnInit {

  filterForm: any

  publishers: Publisher[] | undefined
  categories: Category[] | undefined
  languages: Language[] | undefined
  authors: Author[] | undefined

  filteredLanguages: Language[] | undefined;
  filteredCategories: Category[] | undefined;
  filteredPublishers: Publisher[] | undefined;
  filteredAuthors: Author[] | undefined;

  constructor(private formBuilder: FormBuilder,
              private bookListService: BookListService,
              private dictionaryService: DictionaryService,
              private bookService: BookService) {

  }

  ngOnInit() {
    this.initFilterForm();
    this.initFilterData();
  }

  private initFilterForm() {
    this.filterForm = this.formBuilder.group({
      name: null,
      yearRange: this.formBuilder.group({
        minValue: null,
        maxValue: null
      }),
      priceRange: this.formBuilder.group({
        minValue: null,
        maxValue: null
      }),
      pagesRange: this.formBuilder.group({
        minValue: null,
        maxValue: null
      }),
      authorIds: this.formBuilder.array([]),
      languageIds: this.formBuilder.array([]),
      categoryIds: this.formBuilder.array([]),
      publisherIds: this.formBuilder.array([]),
    })
  }

  onSubmit() {
    this.bookListService.updateFilter(this.filterForm.value)
  }

  onCheckboxChange(event: any, controlName: string) {
    const formArray: FormArray = this.filterForm.get(controlName) as FormArray;
    if (event.target.checked) {
      console.log(event.target.value)
      formArray.push(new FormControl(event.target.value));
    } else {
      let i: number = 0;
      formArray.controls.forEach((ctrl) => {
        if (ctrl.value == event.target.value) {
          formArray.removeAt(i);
          return;
        }
        i++;
      });
    }
  }

  public filterLanguages(event: any): void {
    const value = event.target.value
    if (value === undefined || value.length === 0) {
      this.filteredLanguages = this.languages
    }
    this.filteredLanguages = this.languages?.filter(language => {
      return language.value.toLowerCase().includes(value.toLowerCase())
    })
  }

  public filterCategories(event: any): void {
    const value = event.target.value
    if (value === undefined || value.length === 0) {
      this.filteredCategories = this.categories
    }
    this.filteredCategories = this.categories?.filter(category => {
      return category.value.toLowerCase().includes(value.toLowerCase())
    })
  }

  public filterPublishers(event: any): void {
    const value = event.target.value
    if (value === undefined || value.length === 0
    ) {
      this.filteredPublishers = this.publishers
    }
    this.filteredPublishers = this.publishers?.filter(publisher => {
      return publisher.name.toLowerCase().includes(value.toLowerCase())
    })
  }

  public filterAuthors(event: any): void {
    const value = event.target.value
    if (value === undefined || value.length === 0
    ) {
      this.filteredAuthors = this.authors
    }
    this.filteredAuthors = this.authors?.filter(author => {
      return author.name.toLowerCase().includes(value.toLowerCase())
    })
  }

  private initFilterData() {
    this.bookService.getAllAuthors().subscribe(next => {
      this.authors = next
      this.filteredAuthors = next
    })
    this.bookService.getAllPublishers().subscribe(next => {
      this.publishers = next
      this.filteredPublishers = next
    })
    this.dictionaryService.getAllLanguages().subscribe(next => {
      this.languages = next
      this.filteredLanguages = next
    })
    this.dictionaryService.getAllCategories().subscribe(next => {
      this.categories = next
      this.filteredCategories = next
    })
  }
}
