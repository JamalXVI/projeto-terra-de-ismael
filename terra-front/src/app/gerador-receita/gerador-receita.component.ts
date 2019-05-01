import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl, FormArray } from '@angular/forms';

import { MedicamentoService } from '../core/medicamento/medicamento.service';
import { ElementoDaListaDto } from '../core/models/elemento-da-lista-dto.model';
import { PessoaService } from '../core/pessoa/pessoa.service';
import { forkJoin } from 'rxjs';
import { Pessoa } from '../core/pessoa/pessoa.model';
import { debounceTime, switchMap, tap, finalize } from 'rxjs/operators';
import { CustomErrorStateMatcher } from '../core/CustomErrorStateMatcher.model';
import { Medico } from '../core/medico/medico.model';
import { MedicoService } from '../core/medico/medico.service';
import { FormularioReceita } from '../core/receita/formulario-receita.model';
import { MatStepper, MAT_DATE_LOCALE, MAT_DATE_FORMATS } from '@angular/material';
import { FORMATOS } from '../core/const/constants';
import { MedicamentoPrincipioAtivo } from '../core/medicamento/medicamento-principio-ativo.model';

@Component({
  selector: 'app-gerador-receita',
  templateUrl: './gerador-receita.component.html',
  styleUrls: ['./gerador-receita.component.scss'],
  providers: [
    { provide: MAT_DATE_LOCALE, useValue: 'br' },
    { provide: MAT_DATE_FORMATS, useValue: FORMATOS }
  ],
})
export class GeradorReceitaComponent implements OnInit {
  medicineForm: FormGroup;
  medicamentoForm: FormGroup;
  informationForm: FormGroup;
  tipoMedicamentos: ElementoDaListaDto[] = [];
  principioAtivo: ElementoDaListaDto[] = [];
  itensCarregando: boolean[] = [];
  // Lista de Itens de um Medicamento, contendo todos as misturas válidas daquele tipo
  itensDoMedicamento: ElementoDaListaDto[];
  pessoas: Pessoa[] = [];
  medicos: Medico[] = [];
  medicamentoPrincipioAtivos: MedicamentoPrincipioAtivo[] = [];
  carregando: boolean = false;
  iniciouPesquisa: boolean = false;
  iniciouPesquisaDetalhe: boolean = false;
  formulario: FormularioReceita = new FormularioReceita();
  protected matcher = new CustomErrorStateMatcher();

  constructor(private _formBuilder: FormBuilder,
    private changeDetectionRef: ChangeDetectorRef,
    private _medicamentoService: MedicamentoService,
    private _pessoaService: PessoaService,
    private _medicoService: MedicoService) {
    this.medicineForm = this._formBuilder.group({
      medicamento: new FormControl('', Validators.required),
      medicamentoEscolhido: new FormControl('', Validators.required)
    });
    this.informationForm = this._formBuilder.group({
      pessoa: new FormControl('', Validators.required),
      medico: new FormControl('', Validators.required),
      dataReceita: new FormControl('', [Validators.required])
    });
    this.medicamentoForm = this._formBuilder.group({
      quantidade: new FormControl('', Validators.required),
      items: this._formBuilder.array([this.createPrincipioAtivo()])
    });
    forkJoin([this._medicamentoService.get(), this._medicamentoService.getPrincipioAtivo(''), this._pessoaService.listaPesquisa(''), this._medicoService.get()])
      .subscribe((res: any[]) => {
        this.tipoMedicamentos = res[0];
        this.principioAtivo = res[1];
        this.pessoas = res[2];
        this.medicos = res[3];
      });
    this.informationForm.get('pessoa').valueChanges.pipe(debounceTime(300), tap(() => this.carregando = true),
      switchMap(value => this._pessoaService.listaPesquisa(value).pipe(finalize(() => this.carregando = false))))
      .subscribe(pessoas => this.pessoas = pessoas);
    (<FormArray>this.medicamentoForm.get('items')).controls.forEach((c, i) => {
      c.get('id').valueChanges.pipe(debounceTime(300), tap(() => this.itensCarregando[i] = true),
        switchMap(value => this._medicamentoService.getPrincipioAtivo(value).pipe(finalize(() => this.itensCarregando[i] = false))))
        .subscribe(principioAtivos => this.principioAtivo = principioAtivos)
    });
  }

  createPrincipioAtivo(): FormGroup {
    return this._formBuilder.group({
      id: new FormControl('', [Validators.required, Validators.pattern('/\d+/')]),
      proporcao: new FormControl('', [Validators.required, Validators.pattern('/\d+/')])
    });
  }

  adicionarItem(): void {
    const items: FormArray = this.medicamentoForm.get('items') as FormArray;
    items.push(this.createPrincipioAtivo());
  }

  ngOnInit() {
  }
  /**
   * Finaliza o Formulário de Informações do cliente
   */
  finalizarInformacoesDoPaciente($event, step: MatStepper): void {
    if (this.verificaPessoaValida() && this.verificaMedicoValido() && this.informationForm.get('dataReceita').value instanceof Date && !this.carregando) {
      const paciente: Pessoa = this.informationForm.get('pessoa').value;
      const medico: Medico = this.informationForm.get('medico').value;
      this.formulario.paciente = paciente.codigo;
      this.formulario.medico = medico.codigo;
      step.next();
    }
  }

  reiniciarPrincipioAtivo(): void {
    this.medicamentoPrincipioAtivos = [new MedicamentoPrincipioAtivo()];
  }

  /**
   * Verifica se o Médico é Valído  
   */
  verificaMedicoValido(): boolean {
    let medicoValido = false;
    const medico = this.informationForm.get('medico').value;
    if (medico instanceof Medico) {
      medicoValido = true;
    }
    if (!medicoValido) {
      this.informationForm.get('medico').setErrors({
        naoExiste: true
      })
      return false;
    }
    return true;
  }
  /**
   * Verifica se a Pessoa é Valída
   */
  verificaPessoaValida(): boolean {
    let pessoaValida = false;
    const pessoa = this.informationForm.get('pessoa').value;
    if (pessoa instanceof Pessoa) {
      pessoaValida = true;
    }
    if (!pessoaValida) {
      this.informationForm.get('pessoa').setErrors({
        naoExiste: true
      })
      return false;
    }
    return true;
  }
  /**
   * verifica se o item está carregando
   * @param i o indíce do formulário
   */
  verificarCarregando(i: number): boolean {
    if (!this.itensCarregando[i]) {
      this.itensCarregando[i] = false;
    }
    return this.itensCarregando[i];
  }

  /**
   * Muda o estado do item a ser carregado
   * @param i o indice do item
   * @param estado o novo estado dele
   */
  mudarEstadoCarregando(i: number, estado: boolean): void {
    this.itensCarregando[i] = estado;
  }
}
