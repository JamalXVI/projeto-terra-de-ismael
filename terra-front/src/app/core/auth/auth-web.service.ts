import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

import { AbstractAuthService } from './abstract-auth-service.model';
import { DEFAULT_LOGIN_NAME, DEFAULT_USER_STORE } from '../const/constants';
import { OpenUrl } from '../const/open-url.enum';
import { map, } from 'rxjs/operators';
import { User } from '../user/user.model';
import { UserUrl } from '../const/user-url.enum';

@Injectable({ providedIn: 'root' })
export class AuthWebService extends AbstractAuthService {
    isLoggedIn(): Observable<Boolean> {
        const logado = this.localStorage.getItem(DEFAULT_LOGIN_NAME);
        return of(logado);
    }
    logIn(username: string, password: string): Observable<boolean> {
        const body = `username=${username}&password=${password}`;
        return this.http.post(OpenUrl.LOGIN.toString(), body)
            .pipe(
                map((event: any) => {
                    if (event.tokenDeAcesso) {
                        this.localStorage.setItem(DEFAULT_LOGIN_NAME, JSON.stringify(event));
                    }
                    return true;
                }));
    }
    getUserName(): Observable<String> {
        return this.http.get(UserUrl.WHOAIM).pipe(map(res => {
            const usuario: User = <User> res;
            this.localStorage.setItem(DEFAULT_USER_STORE, usuario);
            return usuario.usuario;
        }));
    }
}
