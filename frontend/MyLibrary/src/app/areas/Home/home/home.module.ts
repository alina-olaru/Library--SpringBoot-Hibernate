import { WelcomeComponent } from '../welcome/welcome.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { Error404Component } from './../error404/error404.component';



@NgModule({
  declarations:[
    WelcomeComponent,
    Error404Component
  ],
  imports: [
    CommonModule,
    HomeRoutingModule,

  ]

})
export class HomeModule { }
