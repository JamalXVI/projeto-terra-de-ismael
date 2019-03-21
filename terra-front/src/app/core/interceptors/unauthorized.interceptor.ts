import { HttpRequest, HttpInterceptor, HttpHandler, HttpEvent, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

import { Observable } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';

@Injectable()
export class UnauthorizedInterceptor implements HttpInterceptor {
    constructor(
        private router: Router
    ) {}

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).pipe(
            tap((event: HttpEvent<any>) => {
              if (event instanceof HttpResponse) {
     
              }
            }, (err: any) => {
              if (err instanceof HttpErrorResponse) {
                if (err.status === 403 || err.status === 401) {
                  this.router.navigate(['login']);
                }
              }
            })
        );
      }
    }
