import { MenuComponent } from './../menu/menu.component';
import { Error404Component } from './../error404/error404.component';
import { WelcomeComponent } from '../welcome/welcome.component';
import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DespreComponent } from '../despre/despre.component';


const routes: Routes = [

  {
    path:"welcome",
    component : WelcomeComponent
  },
  {
    path:"404Error",
    component : Error404Component
  },
  {
    path:"despre",
    component:DespreComponent
  },
  {
    path:"menu",
    component:MenuComponent
  },
  {
    path: "",
    redirectTo: "/404Error",
    pathMatch: "full"
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
