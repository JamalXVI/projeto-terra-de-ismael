import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

import { AbstractAuthService } from './abstract-auth-service.model';
import { DEFAULT_LOGIN_NAME } from '../const/constants';
import { OpenUrl } from '../const/open-url.enum';
import { map, } from 'rxjs/operators';
import { HttpEvent, HttpEventType } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class AuthWebService extends AbstractAuthService {
    isLoggedIn(): Observable<Boolean> {
        const logado = this.localStorage.getItem(DEFAULT_LOGIN_NAME);
        console.log(logado);
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
}
