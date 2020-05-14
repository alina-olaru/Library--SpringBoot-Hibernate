import { CartComponent } from './../cart/cart.component';
import { LayoutComponent } from './../layout/layout.component';
import { QuizzComponent } from './../quizz/quizz.component';
import { ReviewComponent } from './../review/review.component';
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
import { DespreComponent } from '../despre/despre.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { BookDetailsComponent } from '../book-details/book-details.component';
import { CartItemComponent } from '../cart-item/cart-item.component';
import { VouchersUserComponent } from '../VouchersUser/VouchersUser.component';

@NgModule({
  declarations: [
    LayoutComponent,
    WelcomeComponent,
     Error404Component,
     DespreComponent,
     SliderComponent,
     MenuComponent,
     ReviewComponent,
     QuizzComponent,
     CartComponent,
    BookDetailsComponent,
    CartItemComponent,
    VouchersUserComponent

    ],
  imports: [
       CommonModule,
       HomeRoutingModule,
       SharedModule,
  ],
  entryComponents:[
    CartItemComponent
  ]
})
export class HomeModule {}
