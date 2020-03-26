import { RegisterComponent } from './areas/register/register/register.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
{
  path:'register',
  component:RegisterComponent
},
{
  path:'',
  redirectTo:'/register',
  pathMatch:'full'
},
{
  path:'**',
  component: RegisterComponent
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
