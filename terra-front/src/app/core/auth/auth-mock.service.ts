import { Injectable } from '@angular/core';

import { Md5 } from 'ts-md5/dist/md5';
import { Observable, of } from 'rxjs';

import { AbstractAuthService } from './abstract-auth-service.model';
import { DEFAULT_LOGIN_NAME } from '../constants';
import { User } from '../user/user.model';

@Injectable({providedIn: 'root'})
export class AuthMockService extends AbstractAuthService {
    private service: AbstractAuthService;
    isLoggedIn(): Observable<Boolean> {
        const logado = this.localStorage.getItem(DEFAULT_LOGIN_NAME);
        return of(logado);
    }
    logIn(username: string, password: string): boolean {
        const md5 = new Md5();
        const encryptPassword: any = md5.appendStr(password).end();
        const possibleUsers: User[] = this.userService.getUsers().filter((user: User) => user.user === username
            && user.password === encryptPassword);
        if (possibleUsers.length > 0) {
            this.localStorage.setItem(DEFAULT_LOGIN_NAME, possibleUsers[0].id);
            return true;
        } else {
            return false;
        }
    }
}
