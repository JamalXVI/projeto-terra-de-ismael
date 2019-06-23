import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl, FormArray, AbstractControl } from '@angular/forms';
import { DatePipe } from '@angular/common'

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
import { MatStepper, MAT_DATE_LOCALE, MAT_DATE_FORMATS, MatSnackBar } from '@angular/material';
import { FORMATOS } from '../core/const/constants';
import { MedicamentoPrincipioAtivo } from '../core/medicamento/medicamento-principio-ativo.model';
import { FrasesService } from '../core/pipes/frases.service';
import { ReceitaMedicamento } from '../core/medicamento/receita-medicamento.model';
import { ReceitaService } from '../core/receita/receita.service';

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
  listaNoMomentoMedicamento: ElementoDaListaDto[][] = [];
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

  private medicamentos: ReceitaMedicamento[] = [];
  private _medicamentos: ReceitaMedicamento[] = [];
  protected matcher = new CustomErrorStateMatcher();

  constructor(private _formBuilder: FormBuilder,
    private changeDetectionRef: ChangeDetectorRef,
    private _medicamentoService: MedicamentoService,
    private _pessoaService: PessoaService,
    private _medicoService: MedicoService,
    private _frases: FrasesService,
    private _receitaService: ReceitaService,
    private _datePipe: DatePipe,
    private snackBar: MatSnackBar) {
    this.medicineForm = this._formBuilder.group({
      medicamento: new FormControl('', Validators.required),
      medicamentoEscolhido: new FormControl('', Validators.required)
    });
    this.informationForm = this._formBuilder.group({
      pessoa: new FormControl('', Validators.required),
      medico: new FormControl('', Validators.required),
      dataReceita: new FormControl(new Date(), [Validators.required])
    });
    this.medicamentoForm = this._formBuilder.group({
      quantidade: new FormControl('1', [Validators.required, Validators.pattern('\\d+(\.\\d+)?')]),
      peso: new FormControl('', [Validators.required, Validators.pattern('\\d+(\.\\d+)?')]),
      posologia: new FormControl(''),
      validade: new FormControl(new Date(), Validators.required),
      tipo: new FormControl('', Validators.required),
      items: this._formBuilder.array([this.createPrincipioAtivo()])
    });
    forkJoin([this._medicamentoService.get(), this._medicamentoService.getPrincipioAtivo(''), this._pessoaService.listaPesquisa(''), this._medicoService.get()])
      .subscribe((res: any[]) => {
        this.tipoMedicamentos = res[0];
        this.listaNoMomentoMedicamento[0] = res[1];
        this.pessoas = res[2];
        this.medicos = res[3];
      });
    this.informationForm.get('pessoa').valueChanges.pipe(debounceTime(300), tap(() => this.carregando = true),
      switchMap(value => this._pessoaService.listaPesquisa(value).pipe(finalize(() => this.carregando = false))))
      .subscribe(pessoas => this.pessoas = pessoas);
    this.ativarNotificacaoInput();
  }
  ativarNotificacaoInput(): void {
    (<FormArray>this.medicamentoForm.get('items')).controls.forEach((c, i) => {
      c.get('id').valueChanges.pipe(debounceTime(300), tap(() => this.itensCarregando[i] = true),
        switchMap(value => this._medicamentoService.getPrincipioAtivo(value).pipe(finalize(() => this.itensCarregando[i] = false))))
        .subscribe(principioAtivos => {
          this.listaNoMomentoMedicamento[i] = principioAtivos;
        });
    });
  }

  createPrincipioAtivo(): FormGroup {
    return this._formBuilder.group({
      id: new FormControl('', [Validators.required]),
      proporcao: new FormControl('1', [Validators.required, Validators.pattern('\\d{1,2}')])
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
      this.formulario.dataReceita = this._datePipe.transform(this.informationForm.get('dataReceita').value, 'dd/MM/yyyy');
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
  /**
   * Adiciona Princípio Ativo na Lista
   */
  adicionarPrincipioAtivo() {
    if (this.verificarPrincipiosAtivos()) {
      this.adicionarItem();
      this.ativarNotificacaoInput();
    }
  }

  /**
   * Verifica se os princípios ativos estão corretos
   */
  verificarPrincipiosAtivos(): boolean {
    let resultado: boolean = true;
    (<FormArray>this.medicamentoForm.get('items')).controls.forEach((c, i) => {
      const principio: ElementoDaListaDto = <ElementoDaListaDto>c.get('id').value;
      let existe: boolean = false;
      this.listaNoMomentoMedicamento[i].forEach((p: ElementoDaListaDto) => {
        if (p.id == principio.id) {
          existe = true;
        }
      });
      if (c.get('proporcao').hasError && !!c.get('proporcao').errors) {
        resultado = false;
      }
      if (!existe) {
        c.get('id').setErrors({
          naoExiste: true
        })
        resultado = false;
      }
    });
    return resultado;
  }
  removerPrincipioAtivo(i: number): void {
    let controles: AbstractControl[] = (<FormArray>this.medicamentoForm.get('items')).controls;
    if (controles.length > 1) {
      controles.splice(i, 1);
    } else {
      this.snackBar.open(this._frases.converter('ERRO_PELO_MENOS_UM_PRINCIPIO'), '', { duration: 1000 });
    }
  }
  adicionarMedicamento(): void {
    if (this.verificarPrincipiosAtivos() && this.medicamentoForm.valid) {
      const quantidade: number = +this.medicamentoForm.get('quantidade').value;
      const peso: number = +this.medicamentoForm.get('peso').value;
      const posologia: string = this.medicamentoForm.get('posologia').value;
      const data: string = this.medicamentoForm.get('validade').value;
      const tipo: number = +this.medicamentoForm.get('tipo').value;
      const nomeTipo: string = this.tipoMedicamentos.find(tipoMed => tipoMed.id == tipo).nome;
      const principios: MedicamentoPrincipioAtivo[] = [];
      const itemsForm: FormArray = (<FormArray>this.medicamentoForm.get('items'));
      itemsForm.controls.forEach((controle, indice) => {
        const item: ElementoDaListaDto = controle.get('id').value;
        const principio = new MedicamentoPrincipioAtivo({ id: item.id, proporcao: controle.get('proporcao').value, nome: item.nome });
        principios.push(principio);
      });
      let receita: ReceitaMedicamento = new ReceitaMedicamento({
        id: this._medicamentos.length,
        quantidade: quantidade,
        peso: peso,
        posologia: posologia,
        validade: this._datePipe.transform(data, 'dd/MM/yyyy'),
        tipo: tipo,
        principioAtivos: principios,
        nome: nomeTipo
      });
      this._medicamentos.push(receita);
      this.medicamentos = [...this._medicamentos];
      this.changeDetectionRef.detectChanges();
      for (let i = 1; i < itemsForm.controls.length; i++) {
        this.removerPrincipioAtivo(i);
      }
      let controle: AbstractControl = itemsForm.controls[0];
      controle.get('id').setValue("");
      controle.get('proporcao').setValue(1);
    }
  }
  removerItem($event) {
    const id: number = $event;
    let lista: ReceitaMedicamento[] = [...this._medicamentos];
    this._medicamentos = [];
    lista.forEach((item, indice) => {
      if (item.id != id) {
        this._medicamentos.push(Object.assign(lista[indice], { id: this._medicamentos.length }))
      }
    });
    this.medicamentos = [...this._medicamentos];
    this.changeDetectionRef.detectChanges();
    this.snackBar.open(this._frases.converter('REMOVER_ITEM_RECEITA_MENSAGEM'), '', { duration: 1000 });
  }
  proximaEtapa() {
    this.formulario.receita = this._medicamentos;
    this._receitaService.nova(this.formulario);
    this.snackBar.open('TESTE');
  }
}
