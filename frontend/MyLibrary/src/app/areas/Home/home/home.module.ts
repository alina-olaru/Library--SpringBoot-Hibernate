import { MenuComponent } from './../menu/menu.component';
import { SliderComponent } from './../slider/slider.component';
import { MaterialModule } from "./../../../modules/material/material.module";

import { SharedModule } from "./../../../modules/shared/shared.module";
import { WelcomeComponent } from "../welcome/welcome.component";
import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { HomeRoutingModule } from "./home-routing.module";
import { Error404Component } from "./../error404/error404.component";
import { MatInputModule } from "@angular/material/input";
import { MatIconModule } from "@angular/material/icon";
import { NgImageSliderModule } from 'ng-image-slider';
import { DespreComponent } from '../despre/despre.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@NgModule({
  declarations: [
    WelcomeComponent,
     Error404Component,
     DespreComponent,
     SliderComponent,
     MenuComponent
    ],
  imports: [
       CommonModule,
       HomeRoutingModule,
       SharedModule,
       MaterialModule,
       NgImageSliderModule,
       FontAwesomeModule
  ]
})
export class HomeModule {}
