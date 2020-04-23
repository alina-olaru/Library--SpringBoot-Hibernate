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
