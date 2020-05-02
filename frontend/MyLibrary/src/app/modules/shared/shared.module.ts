import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { TruncatePipe } from "../pipes/truncate.pipe";
import { YesNoPipe } from "../pipes/yes-no.pipe";
import { FlexLayoutModule } from "@angular/flex-layout";
import { NgImageSliderModule } from "ng-image-slider";
import { GallerizeModule } from "@ngx-gallery/gallerize";
import { LightboxModule } from "@ngx-gallery/lightbox";
import { GalleryModule } from "@ngx-gallery/core";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [TruncatePipe, YesNoPipe],
  imports: [
    CommonModule,
    FlexLayoutModule,
    NgImageSliderModule,
    GalleryModule,
    LightboxModule,
    GallerizeModule
  ],
  exports: [
    TruncatePipe,
    YesNoPipe,
    FlexLayoutModule,
    NgImageSliderModule,
    GalleryModule,
    LightboxModule,
    GallerizeModule
  ],
})
export class SharedModule {}
