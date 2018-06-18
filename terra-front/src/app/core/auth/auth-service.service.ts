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

@Injectable({ providedIn: 'root' })
export class AuthService {
    constructor(
        @Inject(LocalStorage) protected localStorage: LocalStorage,
        private userService: UserService,
        private router: Router
    ) {

    }
    isLoggedIn(): Observable<Boolean> {
        const logado = this.localStorage.getItem(DEFAULT_LOGIN_NAME);
        return of(logado);
    }
    isAdmin(): Observable<Boolean> {
        const id: Number = this.localStorage.getItem(DEFAULT_LOGIN_NAME);
        if (id) {
            const users: User[] = this.userService.gerUsers().filter(usr => usr.id === id);
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
            const users: User[] = this.userService.gerUsers().filter(usr => usr.id === id);
            if (users && users.length > 0) {
                return users[0].user;
            }
        }
        return 'Não Encontrado!';
    }
    logIn(username: string, password: string): boolean {
        const md5 = new Md5();
        const encryptPassword: any = md5.appendStr(password).end();
        const possibleUsers: User[] = this.userService.gerUsers().filter((user: User) => user.user === username
            && user.password === encryptPassword);
        if (possibleUsers.length > 0) {
            this.localStorage.setItem(DEFAULT_LOGIN_NAME, possibleUsers[0].id);
            return true;
        } else {
            return false;
        }
    }

    logout(): void {
        this.localStorage.deleteItem(DEFAULT_LOGIN_NAME);
    }
}
