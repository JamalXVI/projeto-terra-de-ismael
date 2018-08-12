import { Component, OnInit, Output, ViewChild, ElementRef, EventEmitter, Input } from '@angular/core';
import { trigger, state, style, transition, animate } from '@angular/animations';

import { Observable } from '../../../node_modules/rxjs/internal/Observable';
import { of, Subject } from '../../../node_modules/rxjs';
import { distinctUntilChanged, map } from '../../../node_modules/rxjs/operators';
import { BreakpointObserver, Breakpoints } from '../../../node_modules/@angular/cdk/layout';


@Component({
  selector: 'app-botao-hamburger',
  templateUrl: './botao-hamburger.component.html',
  styleUrls: ['./botao-hamburger.component.css'],
  animations: [trigger('estado1', [
    state('true', style({ transform: 'rotate(0deg)', top: '4px' })),
    state('false', style({ transform: 'rotate(135deg)', top: '9px' })),
    transition('* => *', animate('150ms ease-out'))
  ]),
  [trigger('estado2', [
    state('true', style({ left: '10%', opacity: '1' })),
    state('false', style({ left: '-60px', opacity: '0' })),
    transition('* => *', animate('100ms ease-out'))
  ])],
  [trigger('estado3', [
    state('true', style({ transform: 'rotate(0deg)', top: '14px' })),
    state('false', style({ transform: 'rotate(45deg)', top: '9px' })),
    transition('* => *', animate('100ms ease-out'))
  ])]
  ]
})
export class BotaoHamburgerComponent implements OnInit {
  estado = false;
  @ViewChild('barra') barra: ElementRef;
  @Output() aberta: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Output() antesAbrir: EventEmitter<boolean> = new EventEmitter<boolean>();
  @Input() escutarMudancaBarra: Subject<boolean> = new Subject<boolean>();
  private ativadoAutomatico:boolean = false;
  constructor(private breakpointObserver: BreakpointObserver) {
    // this.alterarEstado(!(window.innerWidth > 600));
    this.breakpointObserver.observe(Breakpoints.Handset)
      .pipe(
        map(result => result.matches)
      ).subscribe((value: boolean) => {
        if (this.estado === value) {
          this.estado = !this.estado;
        }
      });
    this.escutarMudancaBarra.pipe(distinctUntilChanged()).subscribe((valor: boolean) => {
      if (this.estado === valor) {
        this.estado = !this.estado;
        this.ativadoAutomatico = true;
      }
    });
  }

  ngOnInit() {
  }
  mudarEstado() {
    this.alterarEstado(!this.estado);
  }
  alterarEstado(valor: boolean) {
    this.estado = valor;
    if (this.estado) {
      this.antesAbrir.emit(!this.estado);
    }
  }
  finalizarAnimacao() {
    if(!this.ativadoAutomatico){
      this.aberta.emit(!this.estado);
    }else{
      this.ativadoAutomatico = false;
    }
  }
  // @HostListener('window:resize', ['$event'])
  // mudarTamanho($event) {
  //   const largura: number = +$event.target.innerWidth;
  //   if (largura && largura > 600) {
  //     this.alterarEstado(false);
  //   }
  // }
}
