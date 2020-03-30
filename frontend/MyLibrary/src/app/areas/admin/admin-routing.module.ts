import { AuthorsComponent } from './pages/authors/authors.component';

import { LayoutComponent } from './layout/layout.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { BooksComponent } from './pages/books/books.component';


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
        path:"dashboard",
        component:DashboardComponent
      },
      {
        path:"autori",
        component:AuthorsComponent
      },
    {  path:"carti",
      component:BooksComponent
    }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
