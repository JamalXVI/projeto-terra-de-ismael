import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { library } from '@fortawesome/fontawesome-svg-core';
import {
  faNotesMedical, faMortarPestle, faTablets, faPills, faCapsules,
  faPrescriptionBottle, faTint, faEyeDropper, faUser, faLeaf
} from '@fortawesome/free-solid-svg-icons';

import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { AddUserComponent } from './add-user/add-user.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ToolbarComponent } from './toolbar/toolbar.component';
import { TabelaComponent } from './tabela/tabela.component';
import {
  MatTableModule, MatPaginatorModule, MatSortModule, MatSelectModule, MatSidenavModule, MatListModule,
  MatExpansionModule
} from '@angular/material';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AuthInterceptor } from './core/interceptors/auth.interceptor';
import { LoginInterceptor } from './core/interceptors/login.interceptor';
import { MainNavComponent } from './main-nav/main-nav.component';
import { LayoutModule } from '@angular/cdk/layout';

library.add(faNotesMedical, faMortarPestle, faTablets, faPills, faCapsules,
  faPrescriptionBottle, faTint, faEyeDropper, faUser, faLeaf);

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    AddUserComponent,
    PageNotFoundComponent,
    ToolbarComponent,
    TabelaComponent,
    MainNavComponent,
  ],
  imports: [
    HttpClientModule,
    HttpModule,
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    MatCardModule,
    MatButtonModule,
    MatToolbarModule,
    MatInputModule,
    ReactiveFormsModule,
    FormsModule,
    HttpModule,
    MatSnackBarModule,
    MatIconModule,
    MatMenuModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatSelectModule,
    MatExpansionModule,
    LayoutModule,
    MatSidenavModule,
    MatListModule,
    FontAwesomeModule
  ],
  providers: [
    AuthInterceptor,
    LoginInterceptor,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: LoginInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
