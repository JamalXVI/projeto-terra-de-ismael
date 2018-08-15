import { Component, ViewChild } from '@angular/core';
import { BreakpointObserver, Breakpoints, BreakpointState } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { BotaoHamburgerComponent } from '../botao-hamburger/botao-hamburger.component';

@Component({
  selector: 'app-main-nav',
  templateUrl: './main-nav.component.html',
  styleUrls: ['./main-nav.component.css'],
})
export class MainNavComponent {
  @ViewChild(BotaoHamburgerComponent)
  botaoHamburger: BotaoHamburgerComponent;
  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches)
    );
  
  constructor(private breakpointObserver: BreakpointObserver) { }
  mudarEstadoBotao($event: any){
    console.log($event);
    this.botaoHamburger.escutarMudancaBarra.next($event);
  }

}
