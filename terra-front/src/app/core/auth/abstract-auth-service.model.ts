import { Injectable, Inject } from '@angular/core';
import { Observable, of } from 'rxjs';
import { filter } from 'rxjs/operators';

import { LocalStorage } from './local-storage.service';
import { UserService } from '../user/user.service';
import { DEFAULT_LOGIN_NAME } from '../constants';
import { User } from '../user/user.model';
import { Router } from '@angular/router';
import { UserRole } from '../user/user-role.enum';

export abstract class AbstractAuthService {
    constructor(
        @Inject(LocalStorage) protected localStorage: LocalStorage,
        protected userService: UserService,
        protected router: Router
    ) {}
    abstract isLoggedIn(): Observable<Boolean>;
    isAdmin(): Observable<Boolean> {
        const id: Number = this.localStorage.getItem(DEFAULT_LOGIN_NAME);
        if (id) {
            const users: User[] = this.userService.getUsers().filter(usr => usr.id === id);
            if (users && users.length > 0) {
                const user: User = users[0];
                if (user.role === UserRole.ADMIN) {
                    return of(true);
                }
            }
        }
        return of(false);
    }
    getUserName(): String {
        const id: Number = this.localStorage.getItem(DEFAULT_LOGIN_NAME);
        if (id) {
            const users: User[] = this.userService.getUsers().filter(usr => usr.id === id);
            if (users && users.length > 0) {
                return users[0].user;
            }
        }
        return 'Não Encontrado!';
    }
    abstract logIn(username: string, password: string): boolean;

    logout(): void {
        this.localStorage.deleteItem(DEFAULT_LOGIN_NAME);
    }
}
