import { Error404Component } from './../error404/error404.component';
import { WelcomeComponent } from '../welcome/welcome.component';
import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


const routes: Routes = [

  {
    path:"/welcome",
    component : WelcomeComponent
  },
  {
    path:"/404Error",
    component : Error404Component
  },
  {
    path: "",
    redirectTo: "/404Error"
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
