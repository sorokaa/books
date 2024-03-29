import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BookListComponent} from "./features/book/components/book-list/book-list.component";
import {BookDetailsComponent} from "./features/book/components/book-details/book-details.component";
import {OrderListComponent} from "./features/order/components/order-list/order-list.component";
import {CreateBookComponent} from "./features/book/components/create-book/create-book.component";
import {AuthGuard} from "./core/auth/auth.guard";

const routes: Routes = [
  {path: '', redirectTo: '/books', pathMatch: 'full'},
  {path: 'books', component: BookListComponent},
  {path: 'books/:id', component: BookDetailsComponent},
  {path: 'orders', component: OrderListComponent},
  {
    path: 'create-book',
    component: CreateBookComponent,
    canActivate: [AuthGuard],
    data: {roles: ['ROLE_ADMIN']}
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
