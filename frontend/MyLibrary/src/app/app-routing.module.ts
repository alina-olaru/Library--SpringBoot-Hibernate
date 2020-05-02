import { UserGuardService } from './modules/guards/user-guard.service';
import { ConfirmationComponent } from "./areas/confirmation/confirmation.component";
import { WelcomeComponent } from "../app/areas/Home/welcome/welcome.component";
import { LoginComponent } from "./areas/login/login.component";
import { RegisterComponent } from "./areas/register/register.component";
import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { AdminModule } from "./areas/admin/admin.module";
import { AdminGuardService } from "./modules/guards/admin-guard.service";
import { HomeModule } from "./areas/Home/home/home.module";
import { UserModule } from "./areas/User/user/user.module";
const routes: Routes = [
  {
    path: "",
    redirectTo: "home",
    pathMatch: "full",
  },
  {
    path: "register",
    component: RegisterComponent,
  },
  {
    path: "login",
    component: LoginComponent,
  },
  {
    path: "confirm-account",
    component: ConfirmationComponent,
  },
  {
    path: "admin",
    loadChildren: () => AdminModule,
    canActivate: [AdminGuardService],
    canActivateChild: [AdminGuardService],
  },
  {
    path: "home",
    loadChildren: () => HomeModule
  },
  {
    path: "cont",
    loadChildren: () => UserModule,
    canActivate: [UserGuardService],
    canActivateChild: [UserGuardService],
  },

  {
    path: "**",
    component: WelcomeComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
