import { Injectable } from '@angular/core';
import { CanActivate, CanActivateChild, CanLoad, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { take, map, tap } from 'rxjs/operators';

import { AuthService } from './auth-service.service';
import { Observable } from 'rxjs';
import { Route } from '@angular/compiler/src/core';

@Injectable({ providedIn: 'root' })
export class CanActiveAdminRole implements CanActivate {
    constructor(private authService: AuthService,
        private router: Router) {
    }
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | Observable<boolean> | Promise<boolean> {
        return this.admin();
    }
    private admin(): Observable<boolean> {
        return this.authService.isAdmin().pipe(take(1), map(authState => !!authState), tap(authState => {
            if (!authState) {
                this.router.navigate(['home']);
            }
        }));
    }
}
