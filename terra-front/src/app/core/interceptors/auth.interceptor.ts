import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpResponse } from '@angular/common/http/';
import { Injectable, Inject } from '@angular/core';
import { Router } from '../../../../node_modules/@angular/router';

import { Observable } from 'rxjs';

import { LocalStorage } from '../auth/local-storage.service';
import { DEFAULT_LOGIN_NAME } from '../const/constants';
import { authHeaders } from '../const/custom-headers.const';
import { EstadoToken } from '../security/estado-token.model';
import { OpenUrl } from '../const/open-url.enum';
import { map, tap } from 'rxjs/operators';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
    constructor(@Inject(LocalStorage) protected localStorage: LocalStorage,
    private router: Router){
        
    }
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const auth:EstadoToken = new EstadoToken(this.localStorage.getItem(DEFAULT_LOGIN_NAME));
        if(auth && req.url != OpenUrl.LOGIN.toString().toString()){
            const cloned = req.clone({
                headers: authHeaders(auth.tokenDeAcesso)
            });
            return next.handle(cloned);
        }
        return next.handle(req);
    }

}
