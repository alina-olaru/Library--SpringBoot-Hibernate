import { VouchersComponent } from './pages/vouchers/vouchers.component';
import { PublishersComponent } from "./pages/publishers/publishers.component";
import { AuthorsComponent } from "./pages/authors/authors.component";

import { LayoutComponent } from "./layout/layout.component";
import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { DashboardComponent } from "./pages/dashboard/dashboard.component";
import { BooksComponent } from "./pages/books/books.component";
import { CategoryComponent } from "./pages/category/category.component";

const routes: Routes = [
  {
    path: "",
    component: LayoutComponent,
    children: [
      {
        path: "",
        redirectTo: "dashboard"
      },
      {
        path: "dashboard",
        component: DashboardComponent
      },
      {
        path: "autori",
        component: AuthorsComponent
      },
      { path: "carti", component: BooksComponent },
      { path: "edituri", component: PublishersComponent },
      { path: "categorii", component: CategoryComponent },
      { path: "vouchere", component:VouchersComponent}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule {}
