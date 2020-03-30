
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { MaterialModule } from './../../modules/material/material.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { LayoutComponent } from './layout/layout.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { AuthorsComponent } from './pages/authors/authors.component';
import { AddEditAuthorComponent } from './pages/authors/add-edit-author/add-edit-author.component';


@NgModule({
  declarations:
  [LayoutComponent,
    DashboardComponent,
    AuthorsComponent,
    AddEditAuthorComponent],
  imports: [
    CommonModule,
    AdminRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    MaterialModule,
    FontAwesomeModule
  ],
  entryComponents:[
    AddEditAuthorComponent
  ]
})
export class AdminModule { }
