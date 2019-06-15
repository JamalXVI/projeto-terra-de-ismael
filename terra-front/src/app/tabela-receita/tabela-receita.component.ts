import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { ReceitaMedicamento } from '../core/medicamento/receita-medicamento.model';
import { trigger, state, style, transition, animate } from '@angular/animations';
import { MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-tabela-receita',
  templateUrl: './tabela-receita.component.html',
  styleUrls: ['./tabela-receita.component.css'],
  animations: [
    trigger('detalhesExpansao', [
      state('colapsado', style({ height: '0px', minHeight: '0', display: 'none' })),
      state('expandido', style({ height: '*' })),
      transition('expandido <=> colapsado', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class TabelaReceitaComponent implements OnInit {
  private _itens: ReceitaMedicamento[] = [];
  private _dataSource: MatTableDataSource<ReceitaMedicamento> = new MatTableDataSource<ReceitaMedicamento>();
  private detalheExpansao: any;
  @Output()
  public remover: EventEmitter<number> = new EventEmitter<number>();
  @Input() set itens(itens: ReceitaMedicamento[]) {
    if (!!itens && itens.length > 0) {
      this._itens = itens;
      this._dataSource.data = this._itens;
    }
  }

  private expandir(linha: any) {
    this.detalheExpansao = this.detalheExpansao == linha ? null : this.detalheExpansao = linha;

  }
  private posologia(medicamento: ReceitaMedicamento) {
    return !medicamento.posologia ? 'SEM_POSOLOGIA' : medicamento.posologia;
  }

  private colunas: String[] = ["tipo", "quantidade", "peso"];
  private colunasPrincipio: String[] = ["nome", "proporcao"];
  constructor() {

  }

  removerItem(id: number) {
    this.remover.emit(id);
  }

  ngOnInit() {
  }
}
