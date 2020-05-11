import { ThemeSelectorService } from './modules/theme-selector/theme-selector.service';
import { CartService } from './areas/Home/cart/cart.service';
import { UserModule } from './areas/User/user/user.module';
import { ErrorInterceptorService } from './modules/interceptors/error-interceptor.service';
import { LoadingService } from './modules/loading-spinner/loading.service';
import { ToastrService } from './services/toastr.service';
import { RegisterService } from './areas/register/register.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './areas/register/register.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from '../app/modules/material/material.module';
import {
  ReactiveFormsModule,
  Validators,
  FormBuilder,
  FormsModule,
} from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';
import { LoadingSpinnerComponent } from './modules/loading-spinner/loading-spinner.component';
import { SharedModule } from './modules/shared/shared.module';
import { GlobalVarService } from './services/global-var.service';
import { CookieService } from 'ngx-cookie-service';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { LoginComponent } from './areas/login/login.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ConfirmationComponent } from './areas/confirmation/confirmation.component';
import { BearerAuthInterceptorService } from './modules/interceptors/bearer-auth-interceptor.service';
import { AccountOverviewComponent } from './areas/User/account-overview/account-overview.component';
import { AddAddressComponent } from './areas/User/addresses-book/add-address/add-address.component';
import { ForgetPasswordComponent } from './areas/forget-password/forget-password.component';
import { ChangePasswordComponent } from './areas/change-password/change-password.component';
import { CartItemComponent } from './areas/Home/cart-item/cart-item.component';




@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoadingSpinnerComponent,
    LoginComponent,
    ConfirmationComponent,
    AccountOverviewComponent,
    AddAddressComponent,
    ForgetPasswordComponent,
    ChangePasswordComponent,


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule,
    UserModule


  ],
  providers: [
    FormBuilder,
    CookieService,
    Validators,
    GlobalVarService,
    ToastrService,
    LoadingService,
    RegisterService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: BearerAuthInterceptorService,
      multi: true,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ErrorInterceptorService,
      multi: true,
    },
    CartService,
    ThemeSelectorService,
    CartService
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
