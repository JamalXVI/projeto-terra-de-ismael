import { Injectable } from '@angular/core';
import { CanActivate, CanActivateChild, CanLoad, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { take, map, tap } from 'rxjs/operators';

import { AuthService } from './auth-service.service';
import { Observable } from 'rxjs';
import { Route } from '@angular/compiler/src/core';

@Injectable({ providedIn: 'root' })
export class CanActiveAuthGuard implements CanActivate, CanActivateChild, CanLoad {
    constructor(private authService: AuthService,
        private router: Router) {
    }
    canLoad(route: Route): boolean | Promise<boolean> | Observable<boolean> {
        return this.authenticade();
    }
    canActivateChild(childRoute: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | Observable<boolean> | Promise<boolean> {
        return this.authenticade();
    }
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | Observable<boolean> | Promise<boolean> {
        return this.authenticade();
    }
    private authenticade(): Observable<boolean> {
        return this.authService.isLoggedIn().pipe(take(1), map(authState => !!authState), tap(authState => {
            if (!authState) {
                this.router.navigate(['/login'], { queryParams: { mensagem: 'login-invalido' } });
            }
        }));
    }
}
