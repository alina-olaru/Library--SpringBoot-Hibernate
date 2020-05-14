import { PricePipe } from './../pipes/price.pipe';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { ThemeSelectorComponent } from './../theme-selector/theme-selector.component';
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
import { MaterialModule } from '../material/material.module';

@NgModule({
  declarations: [TruncatePipe, YesNoPipe, ThemeSelectorComponent,PricePipe],
  imports: [
    CommonModule,
    FlexLayoutModule,
    NgImageSliderModule,
    GalleryModule,
    LightboxModule,
    GallerizeModule,
    MaterialModule,
    FontAwesomeModule,
    FlexLayoutModule
  ],
  exports: [
    ThemeSelectorComponent,
    TruncatePipe,
    YesNoPipe,
    FlexLayoutModule,
    NgImageSliderModule,
    GalleryModule,
    LightboxModule,
    GallerizeModule,
    MaterialModule,
    FontAwesomeModule,
    FlexLayoutModule,
    PricePipe
  ],
})
export class SharedModule {}
