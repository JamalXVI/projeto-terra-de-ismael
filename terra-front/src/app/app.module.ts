import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { FlexLayoutModule } from '@angular/flex-layout';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';

import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatInputModule } from '@angular/material/input';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { LayoutModule } from '@angular/cdk/layout';
import { library } from '@fortawesome/fontawesome-svg-core';
import {
  faNotesMedical, faMortarPestle, faTablets, faPills, faCapsules,
  faPrescriptionBottle, faTint, faEyeDropper, faUser, faLeaf, faUserPlus, faArrowRight
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
  MatExpansionModule, MatStepperModule, MatAutocompleteModule, MatProgressSpinnerModule, MatTooltipModule
} from '@angular/material';
import { AuthInterceptor } from './core/interceptors/auth.interceptor';
import { LoginInterceptor } from './core/interceptors/login.interceptor';
import { MainNavComponent } from './main-nav/main-nav.component';
import { BotaoHamburgerComponent } from './botao-hamburger/botao-hamburger.component';
import { GeradorReceitaComponent } from './gerador-receita/gerador-receita.component';
import { RouterModule } from '@angular/router';
import { MedicamentoMockService } from './core/medicamento/medicamento-mock.service';
import { MedicamentoService } from './core/medicamento/medicamento.service';
import { MedicamentoWebService } from './core/medicamento/medicamento-web.service';
import { PessoaService } from './core/pessoa/pessoa.service';
import { PessoaMockService } from './core/pessoa/pessoa-mock.service';
import { PessoaWebService } from './core/pessoa/pessoa-web.service';

library.add(faNotesMedical, faMortarPestle, faTablets, faPills, faCapsules,
  faPrescriptionBottle, faTint, faEyeDropper, faUser, faLeaf, faUserPlus, faArrowRight);

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
    BotaoHamburgerComponent,
    GeradorReceitaComponent
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
    MatTooltipModule,
    MatProgressSpinnerModule,
    MatAutocompleteModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule,
    MatSnackBarModule,
    MatIconModule,
    MatMenuModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatSelectModule,
    MatStepperModule,
    MatExpansionModule,
    LayoutModule,
    MatSidenavModule,
    MatListModule,
    FlexLayoutModule,
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
    },
    MedicamentoMockService,
    MedicamentoWebService,
    MedicamentoService,
    PessoaMockService,
    PessoaWebService,
    PessoaService,
  ],
  schemas: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
