import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './areas/register/register/register.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { MaterialModule } from '../app/modules/material/material.module';
import { ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';
import { LoadingSpinnerComponent } from './modules/loading-spinner/loading-spinner.component';
import { TruncatePipe } from './modules/pipes/truncate.pipe';


@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoadingSpinnerComponent,
    TruncatePipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    MaterialModule,
    FlexLayoutModule
  ],
  providers: [



  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
