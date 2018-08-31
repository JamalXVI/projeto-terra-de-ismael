import { Component, ViewChild } from '@angular/core';
import { BreakpointObserver, Breakpoints, BreakpointState } from '@angular/cdk/layout';

import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BotaoHamburgerComponent } from '../botao-hamburger/botao-hamburger.component';
import { AuthService } from '../core/auth/auth-service.service';
import { Router } from '../../../node_modules/@angular/router';

@Component({
  selector: 'app-main-nav',
  templateUrl: './main-nav.component.html',
  styleUrls: ['./main-nav.component.css'],
})
export class MainNavComponent {
  @ViewChild(BotaoHamburgerComponent)
  botaoHamburger: BotaoHamburgerComponent;
  username: String = '';
  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches)
    );

  constructor(private authService: AuthService,
    private breakpointObserver: BreakpointObserver,
    private router: Router) {
    this.authService.getUserName().subscribe(usr => this.username = usr.toUpperCase());
  }
  mudarEstadoBotao($event: any) {
    this.botaoHamburger.escutarMudancaBarra.next($event);
  }
  logout() {
    this.authService.logout();
    this.router.navigate(['/login'], { queryParams: { mensagem: 'deslogado' } });
  }
}
