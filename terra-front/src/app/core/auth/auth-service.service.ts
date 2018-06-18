import { Injectable, Inject } from '@angular/core';
import { Observable, of } from 'rxjs';
import { filter } from 'rxjs/operators';

import { Md5 } from 'ts-md5/dist/md5';

import { LocalStorage } from './local-storage.service';
import { UserService } from '../user/user.service';
import { DEFAULT_LOGIN_NAME } from '../constants';
import { User } from '../user/user.model';
import { Router } from '@angular/router';
import { UserRole } from '../user/user-role.enum';
import { AbstractAuthService } from './abstract-auth-service.model';
import { AuthFactoryService } from './auth-factory.service';

@Injectable({ providedIn: 'root' })
export class AuthService extends AbstractAuthService {
    private service: AbstractAuthService;
    constructor(
        @Inject(LocalStorage) protected localStorage: LocalStorage,
        protected userService: UserService,
        protected router: Router,
        private factory: AuthFactoryService
    ) {
        super(localStorage, userService, router);
        this.service = factory.getService();
    }
    isLoggedIn(): Observable<Boolean> {
       return this.service.isLoggedIn();
    }
    logIn(username: string, password: string): boolean {
        return this.service.logIn(username, password);
    }
}
