import { Injectable } from '@angular/core';

import { Md5 } from 'ts-md5/dist/md5';
import { Observable, of } from 'rxjs';

import { AbstractAuthService } from './abstract-auth-service.model';
import { DEFAULT_LOGIN_NAME } from '../const/constants';
import { User } from '../user/user.model';

@Injectable({providedIn: 'root'})
export class AuthMockService extends AbstractAuthService {
    private service: AbstractAuthService;
    isLoggedIn(): Observable<Boolean> {
        const logado = this.localStorage.getItem(DEFAULT_LOGIN_NAME);
        return of(logado);
    }
    logIn(username: string, password: string): Observable<boolean> {
        const md5 = new Md5();
        const encryptPassword: any = md5.appendStr(password).end();
        const possibleUsers: User[] = this.userService.getUsers().filter((user: User) => user.usuario === username
            && user.senha === encryptPassword);
        if (possibleUsers.length > 0) {
            this.localStorage.setItem(DEFAULT_LOGIN_NAME, possibleUsers[0].id);
            return of(true);
        } else {
            return of(false);
        }
    }
    getUserName(): Observable<String> {
        const id: Number = this.localStorage.getItem(DEFAULT_LOGIN_NAME);
        if (id) {
            const users: User[] = this.userService.getUsers().filter(usr => usr.id === id);
            if (users && users.length > 0) {
                return of(users[0].usuario);
            }
        }
        return of('Não Encontrado!');
    }
}
