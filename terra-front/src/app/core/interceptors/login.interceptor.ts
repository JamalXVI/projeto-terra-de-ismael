import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpResponse, HttpErrorResponse } from '@angular/common/http/';
import { Observable, throwError } from 'rxjs';
import { Injectable } from '@angular/core';
import { OpenUrl } from '../const/open-url.enum';
import { LOGIN_HEADERS } from '../const/custom-headers.const';
import { catchError, tap } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable()
export class LoginInterceptor implements HttpInterceptor {
    constructor(private router: Router) { }
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (req.url === OpenUrl.LOGIN.toString().toString()) {
            const cloned = req.clone({
                headers: LOGIN_HEADERS
            });
            return next.handle(cloned);
        }
        return next.handle(req);
    }

}
