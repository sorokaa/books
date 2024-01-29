import {Component, OnDestroy, OnInit} from "@angular/core";
import {FormArray, FormBuilder, FormControl} from "@angular/forms";
import {BookService} from "../../services/book.service";
import {Language} from "../../models/language.model";
import {Publisher} from "../../models/publisher.model";
import {Author} from "../../models/author.model";
import {Category} from "../../models/category.model";
import {Router} from "@angular/router";
import {FileService} from "../../services/file.service";
import {HttpResponse} from "@angular/common/http";
import {DictionaryService} from "../../services/dictionary.service";
import {BookStatus} from "../../models/book-status.model";

@Component({
  selector: 'create-book',
  templateUrl: './create-book.component.html',
  styleUrls: ['./create-book.component.css']
})
export class CreateBookComponent implements OnInit, OnDestroy {

  bookForm: any
  currentImage: any | undefined

  submitted: boolean = false

  categories: Category[] = []
  languages: Language[] = []
  publishers: Publisher[] = []
  authors: Author[] = []
  availableStatuses: BookStatus[] = []

  constructor(private formBuilder: FormBuilder,
              private bookService: BookService,
              private router: Router,
              private fileService: FileService,
              private dictionaryService: DictionaryService) {
    this.availableStatuses = [BookStatus.AVAILABLE, BookStatus.PRE_ORDER, BookStatus.SOLD_OUT]
  }

  public ngOnInit() {
    this.bookForm = this.formBuilder.group({
      name: null,
      year: null,
      pages: null,
      price: null,
      status: null,
      description: null,
      pictureId: null,
      languageId: null,
      categoryIds: this.formBuilder.array([]),
      publisherId: null,
      authorIds: this.formBuilder.array([])
    })
    this.bookService.getAllAuthors().subscribe(
      next => this.authors = next.slice(0, 10)
    );
    this.bookService.getAllPublishers().subscribe(
      next => this.publishers = next
    );
    this.dictionaryService.getAllLanguages(null).subscribe(
      next => this.languages = next
    );
    this.dictionaryService.getAllCategories(null).subscribe(
      next => this.categories = next
    );
  }

  public ngOnDestroy() {
    if (this.submitted) {
      return;
    }
    this.fileService.deleteById(this.currentImage.id)
  }

  public onSubmit() {
    this.bookService.create(this.bookForm.value).subscribe(
      next => {
        this.submitted = true
        this.router.navigate(['/books'])
      }
    )
  }

  public onFileChanged(event: any) {
    this.fileService.uploadImage(event.target.files[0]).subscribe(
      (event: any) => {
        if (event instanceof HttpResponse) {
          this.currentImage = event.body;
          this.bookForm.controls['pictureId'].setValue(this.currentImage.id)
        }
      },
    )
  }

  public getFileUrl(id: number) {
    return this.fileService.getFileUrl(id)
  }

  public onCheckboxChange(event: any, formName: string) {
    const formArray: FormArray = this.bookForm.get(formName) as FormArray;
    if (event.target.checked) {
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

  public onRadioChange(event: any, formName: string) {

  }

  public filterCategories(event: any): void {
    const value = event.target.value
    this.dictionaryService.getAllCategories(value).subscribe(
      next => this.categories = next
    )
  }

  public filterPublishers(event: any): void {
    this.bookService.getAllPublishers().subscribe(
      next => this.publishers = next
    ) // todo
  }

  public filterAuthors(event: any): void {
    this.bookService.getAllAuthors().subscribe(
      next => this.authors = next
    )
  }

  public filterLanguages(event: any) {
    const value = event.target.value
    this.dictionaryService.getAllLanguages(value).subscribe(
      next => this.languages = next
    )
  }
}
