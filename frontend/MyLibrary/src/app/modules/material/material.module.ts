import { MatButtonModule } from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatProgressBarModule} from '@angular/material/progress-bar';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    MatButtonModule,
    MatCardModule,
    MatDatepickerModule,
    MatSidenavModule,
    MatFormFieldModule,
    MatInputModule,
    MatProgressBarModule


  ],
  exports: [
    MatButtonModule,
    MatCardModule,
    MatDatepickerModule,
    MatSidenavModule,
    MatFormFieldModule,
    MatInputModule,
    MatProgressBarModule

  ]
})
export class MaterialModule { }
