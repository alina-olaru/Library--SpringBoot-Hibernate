import { MatButtonModule } from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatDividerModule} from '@angular/material/divider';
import {MatListModule} from '@angular/material/list';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatMenuModule} from '@angular/material/menu';
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
    MatProgressBarModule,
    MatCheckboxModule,
    MatTooltipModule,
    MatDividerModule,
    MatListModule,
    MatToolbarModule,
    MatMenuModule

  ],
  exports: [
    MatButtonModule,
    MatCardModule,
    MatDatepickerModule,
    MatSidenavModule,
    MatFormFieldModule,
    MatInputModule,
    MatProgressBarModule,
    MatCheckboxModule,
    MatTooltipModule,
    MatDividerModule,
    MatListModule,
    MatToolbarModule,
    MatMenuModule

  ]
})
export class MaterialModule { }
