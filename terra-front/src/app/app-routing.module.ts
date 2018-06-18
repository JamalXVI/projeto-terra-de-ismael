import { Routes, RouterModule } from '@angular/router';

import { NgModule } from '@angular/core';

import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { AddUserComponent } from './add-user/add-user.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { CanActiveAuthGuard } from './core/auth/can-active-auth-guard.service';
import { CanActiveAdminRole } from './core/auth/can-active-admin-role-guard.service';

const routes: Routes = [
    { path: '', pathMatch: 'full', redirectTo: 'login' },
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: 'home',
        component: HomeComponent,
        canActivate: [CanActiveAuthGuard]
    },
    {
        path: 'add',
        component: AddUserComponent,
        canActivate: [CanActiveAuthGuard, CanActiveAdminRole],

    },
    {
        path: '',
        component: PageNotFoundComponent
    }
];
@NgModule({
    imports: [
        RouterModule.forRoot(routes)
    ],
    exports: [
        RouterModule
    ],
})
export class AppRoutingModule { }
