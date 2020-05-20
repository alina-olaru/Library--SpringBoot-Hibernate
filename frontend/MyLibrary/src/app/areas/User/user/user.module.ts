import { MyVouchersComponent } from './../MyVouchers/MyVouchers.component';
import { LayoutComponent } from './../layout/layout.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { SharedModule } from './../../../modules/shared/shared.module';
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
import { AddBookViaOCRComponent } from '../add-book-via-ocr/add-book-via-ocr.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MaterialModule } from 'src/app/modules/material/material.module';


@NgModule({
  declarations: [
    UserMenuComponent,
    OrderHistoryComponent,
    PersonalDataComponent,
    AddressesBookComponent,
    WishlistComponent,
    MyBooksComponent,
    RecommendationsComponent,
    MyReviewsComponent,
    AddBookViaOCRComponent,
    LayoutComponent,
    MyVouchersComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    SharedModule,
    FontAwesomeModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    MaterialModule,


  ],
  exports:[
    UserMenuComponent
  ],
  entryComponents:[
    AddBookViaOCRComponent

  ]
})
export class UserModule { }
