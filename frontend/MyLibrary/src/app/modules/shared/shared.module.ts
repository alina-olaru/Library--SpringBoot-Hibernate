import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TruncatePipe } from '../pipes/truncate.pipe';
import { YesNoPipe } from '../pipes/yes-no.pipe';


@NgModule({
  declarations: [TruncatePipe,YesNoPipe],
  imports: [
    CommonModule,
  ],
  exports: [TruncatePipe,YesNoPipe]
})
export class SharedModule { }
