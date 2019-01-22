import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Ng4LoadingSpinnerModule } from 'ng4-loading-spinner';
import { AuthenticationService } from './login/authentication.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { MainComponent } from './main.component';
import { HeaderComponent } from './header/header.component';
import { CoursComponent } from './cours/cours.component';
import { CoursService } from './cours/cours.service';
import {MatNativeDateModule} from '@angular/material';
import {DemoMaterialModule} from '../material-module';


@NgModule({
  declarations: [
    MainComponent,
    AppComponent,
    HomeComponent,
    HeaderComponent,
    CoursComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    DemoMaterialModule,
    MatNativeDateModule,
    ReactiveFormsModule,
    Ng4LoadingSpinnerModule.forRoot()
  ],
  providers: [
    AuthenticationService,
    CoursService
  ],
  bootstrap: [MainComponent]
})
export class AppModule { }
