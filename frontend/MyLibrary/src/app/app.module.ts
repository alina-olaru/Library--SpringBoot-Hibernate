import { LoadingService } from './modules/loading-spinner/loading.service';
import { ToastrService } from './services/toastr.service';
import { RegisterService } from './areas/register/register.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './areas/register/register.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { MaterialModule } from '../app/modules/material/material.module';
import { ReactiveFormsModule, Validators, FormBuilder, FormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';
import { LoadingSpinnerComponent } from './modules/loading-spinner/loading-spinner.component';
import { SharedModule } from './modules/shared/shared.module';
import { GlobalVarService } from './services/global-var.service';
import { CookieService } from 'ngx-cookie-service';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { LoginComponent } from './areas/login/login.component';
import { HttpClientModule } from '@angular/common/http';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {MatInputModule} from '@angular/material/input';





@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoadingSpinnerComponent,
    LoginComponent


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MaterialModule,
    SharedModule,
    FontAwesomeModule,
    MatDatepickerModule
,MatNativeDateModule,MatInputModule

  ],
  providers: [
    FormBuilder,
    CookieService,
    Validators,
    GlobalVarService,
    ToastrService,
    LoadingService,
    RegisterService
    //TODO ADD SERVICES



  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
