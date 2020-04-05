import { MaterialModule } from "./../../../modules/material/material.module";
import { DespreComponent } from "./../despre/despre.component";
import { SharedModule } from "./../../../modules/shared/shared.module";
import { WelcomeComponent } from "../welcome/welcome.component";
import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { HomeRoutingModule } from "./home-routing.module";
import { Error404Component } from "./../error404/error404.component";
import { MatInputModule } from "@angular/material/input";
import { MatIconModule } from "@angular/material/icon";

@NgModule({
  declarations: [WelcomeComponent, Error404Component, DespreComponent],
  imports: [CommonModule, HomeRoutingModule, SharedModule, MaterialModule]
})
export class HomeModule {}
