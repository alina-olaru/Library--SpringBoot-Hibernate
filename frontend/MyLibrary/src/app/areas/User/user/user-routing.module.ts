import { LayoutComponent as UserLayoutComponent } from './../layout/layout.component';
import { WishlistComponent } from './../wishlist/wishlist.component';
import { LayoutComponent } from './../../Home/layout/layout.component';
import { RecommendationsComponent } from './../recommendations/recommendations.component';
import { PersonalDataComponent } from './../personal-data/personal-data.component';
import { OrderHistoryComponent } from './../order-history/order-history.component';
import { MyReviewsComponent } from './../my-reviews/my-reviews.component';
import { MyBooksComponent } from './../my-books/my-books.component';
import { AddressesBookComponent } from './../addresses-book/addresses-book.component';
import { AccountOverviewComponent } from './../account-overview/account-overview.component';
import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MyVouchersComponent } from '../MyVouchers/MyVouchers.component';

const routes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      {
        path: '',
        component: UserLayoutComponent,
        children: [
          {
            path: 'adrese',
            component: AddressesBookComponent,
          },{
            path:'vouchere',
            component:MyVouchersComponent

          },
          {
            path: 'bibiotecapersonala',
            component: MyBooksComponent,
          },
          {
            path: 'recenzii',
            component: MyReviewsComponent,
          },
          {
            path: 'istoric',
            component: OrderHistoryComponent,
          },
          {
            path: 'datepersonale',
            component: PersonalDataComponent,
          },
          {
            path: 'recomandari',
            component: RecommendationsComponent,
          },
          {
            path: 'wishlist',
            component: WishlistComponent,
          },
          {
            path: 'accountOverview',
            component: AccountOverviewComponent
          }
        ]
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UserRoutingModule {}
