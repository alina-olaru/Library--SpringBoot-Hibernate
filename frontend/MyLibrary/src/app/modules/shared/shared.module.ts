
import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { TruncatePipe } from "../pipes/truncate.pipe";
import { YesNoPipe } from "../pipes/yes-no.pipe";
import { FlexLayoutModule } from "@angular/flex-layout";
import { NgImageSliderModule } from 'ng-image-slider';

@NgModule({
  declarations: [TruncatePipe, YesNoPipe],
  imports: [CommonModule, FlexLayoutModule, NgImageSliderModule],
  exports: [
    TruncatePipe,
    YesNoPipe,
    FlexLayoutModule,
    NgImageSliderModule
  ]
})
export class SharedModule {}
