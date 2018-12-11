import { Inject } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';

import { LocalStorage } from './local-storage.service';
import { UserService } from '../user/user.service';
import { DEFAULT_LOGIN_NAME } from '../const/constants';
import { User } from '../user/user.model';
import { Router } from '@angular/router';
import { UserRole } from '../user/user-role.enum';
import { ErrorsService } from '../errors/errors.service';

export abstract class AbstractAuthService {
    protected users: User[] = [];
    constructor(
        @Inject(LocalStorage) protected localStorage: LocalStorage,
        protected userService: UserService,
        protected router: Router,
        protected http: HttpClient,
        protected errorService: ErrorsService
    ) {
        this.userService.getUsers().subscribe(users => {
            this.users = users
        });
    }
    abstract isLoggedIn(): Observable<Boolean>;
    isAdmin(): Observable<Boolean> {
        const id: Number = this.localStorage.getItem(DEFAULT_LOGIN_NAME);
        if (id) {
            const users: User[] = this.users.filter(usr => usr.id === id);
            if (users && users.length > 0) {
                const user: User = users[0];
                if (user.role === UserRole.ADMIN) {
                    return of(true);
                }
            }
        }
        return of(false);
    }
    abstract getUserName(): Observable<String>;
    abstract logIn(username: string, password: string): Observable<boolean>;

    logout(): void {
        this.localStorage.deleteItem(DEFAULT_LOGIN_NAME);
    }
}
