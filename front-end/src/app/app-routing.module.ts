import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AppComponent } from './app.component';
import { CoursComponent } from './cours/cours.component';

const routes: Routes = [
  {path: '', component: AppComponent},
  {path: 'login', component: AppComponent},
  {path: 'cours', component: CoursComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
