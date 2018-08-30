import { Injectable, Inject } from '@angular/core';
import { Observable } from 'rxjs';

import { LocalStorage } from './local-storage.service';
import { UserService } from '../user/user.service';
import { Router } from '@angular/router';
import { AbstractAuthService } from './abstract-auth-service.model';
import { AuthFactoryService } from './auth-factory.service';
import { HttpClient } from '@angular/common/http';
import { ErrorsService } from '../errors/errors.service';

@Injectable({ providedIn: 'root' })
export class AuthService extends AbstractAuthService {
    private service: AbstractAuthService;
    protected http: HttpClient;
    constructor(
        @Inject(LocalStorage) protected localStorage: LocalStorage,
        protected userService: UserService,
        protected router: Router,
        http: HttpClient,
        errorService: ErrorsService,
        factory: AuthFactoryService
    ) {
        super(localStorage, userService, router, http, errorService);
        this.service = factory.getService();
    }
    isLoggedIn(): Observable<Boolean> {
        return this.service.isLoggedIn();
    }
    logIn(username: string, password: string): Observable<boolean> {
        return this.service.logIn(username, password);
    }

    getUserName(): Observable<String> {
        return this.service.getUserName();
    }
}
