import { LoginComponent } from './areas/login/login.component';
import { RegisterComponent } from "./areas/register/register.component";
import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router";
import { AdminModule } from './areas/admin/admin.module';
import { AdminGuardService } from './modules/guards/admin-guard.service';

const routes: Routes = [
  {
    path: "register",
    component: RegisterComponent
  },
  {
    path: "login",
    component: LoginComponent
  },
  {
    path: 'admin',
    loadChildren: () => AdminModule,
    canActivate: [AdminGuardService],
    canActivateChild: [AdminGuardService]
  },
  {
    path: "",
    redirectTo: "/register",
    pathMatch: "full"
  },
  {
    path: "**",
    component: RegisterComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
