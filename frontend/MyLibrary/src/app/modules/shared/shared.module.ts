
import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { TruncatePipe } from "../pipes/truncate.pipe";
import { YesNoPipe } from "../pipes/yes-no.pipe";
import { FlexLayoutModule } from "@angular/flex-layout";

@NgModule({
  declarations: [TruncatePipe, YesNoPipe],
  imports: [CommonModule, FlexLayoutModule],
  exports: [
    TruncatePipe,
    YesNoPipe,
    FlexLayoutModule
  ]
})
export class SharedModule {}
