import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { OrderHistoryComponent } from '../order-history/order-history.component';
import { UserMenuComponent } from '../user-menu/user-menu.component';
import { PersonalDataComponent } from '../personal-data/personal-data.component';
import { AddressesBookComponent } from '../addresses-book/addresses-book.component';
import { WishlistComponent } from '../wishlist/wishlist.component';
import { MyBooksComponent } from '../my-books/my-books.component';
import { RecommendationsComponent } from '../recommendations/recommendations.component';
import { MyReviewsComponent } from '../my-reviews/my-reviews.component';


@NgModule({
  declarations: [
    UserMenuComponent,
    OrderHistoryComponent,
    PersonalDataComponent,
    AddressesBookComponent,
    WishlistComponent,
    MyBooksComponent,
    RecommendationsComponent,
    MyReviewsComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule,

  ]
})
export class UserModule { }
