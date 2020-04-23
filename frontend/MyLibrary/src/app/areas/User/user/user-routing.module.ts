import { RecommendationsComponent } from './../recommendations/recommendations.component';
import { PersonalDataComponent } from './../personal-data/personal-data.component';
import { OrderHistoryComponent } from './../order-history/order-history.component';
import { MyReviewsComponent } from './../my-reviews/my-reviews.component';
import { MyBooksComponent } from './../my-books/my-books.component';
import { AddressesBookComponent } from './../addresses-book/addresses-book.component';
import { AccountOverviewComponent } from './../account-overview/account-overview.component';
import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [
  {
    path: "",
    redirectTo: "accountOverview"
  },

  {
    path : "accountOverview",
    component : AccountOverviewComponent
  }
,
{
  path:"adrese",
  component:AddressesBookComponent
},
{
  path:"bilbiotecapersonala",
  component:MyBooksComponent
},
{
  path:"Recenzii",
  component:MyReviewsComponent
},
{
  path:"istoric",
  component:OrderHistoryComponent
},
{
  path:"datepersonale",
  component:PersonalDataComponent
},
{
path:"recomandari",
component:RecommendationsComponent
}
  // {
  //   path: "register",
  //   component: RegisterComponent
  // },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
