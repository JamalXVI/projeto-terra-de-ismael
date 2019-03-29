import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';

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
import {MatStepper, MAT_DATE_LOCALE, MAT_DATE_FORMATS } from '@angular/material';
import { FORMATOS } from '../core/const/constants';

@Component({
  selector: 'app-gerador-receita',
  templateUrl: './gerador-receita.component.html',
  styleUrls: ['./gerador-receita.component.scss'],
  providers: [
    {provide: MAT_DATE_LOCALE, useValue: 'br'},
    {provide: MAT_DATE_FORMATS, useValue: FORMATOS }
  ],
})
export class GeradorReceitaComponent implements OnInit {
  medicineForm: FormGroup;
  informationForm: FormGroup;
  medicamentos: ElementoDaListaDto[] = [];
  pessoas: Pessoa[] = [];
  medicos: Medico[] = [];
  carregando: boolean = false;
  formulario: FormularioReceita = new FormularioReceita();
  protected matcher = new CustomErrorStateMatcher();
  constructor(private _formBuilder: FormBuilder,
    private _medicamentoService: MedicamentoService,
    private _pessoaService: PessoaService,
    private _medicoService: MedicoService) {
    this.medicineForm = this._formBuilder.group({
      medicamento: new FormControl('', Validators.required)
    });
    this.informationForm = this._formBuilder.group({
      pessoa: new FormControl('', Validators.required),
      medico: new FormControl('', Validators.required)
    });
    forkJoin([this._medicamentoService.get(), this._pessoaService.listaPesquisa(''), this._medicoService.get()])
      .subscribe((res: any[]) => {
        this.medicamentos = res[0];
        this.pessoas = res[1];
        this.medicos = res[2];
      })
    this._medicamentoService.get().subscribe(medicamentos => {
      this.medicamentos = medicamentos;
    });
    this.informationForm.get('pessoa').valueChanges.pipe(debounceTime(300), tap(() => this.carregando = true),
      switchMap(value => this._pessoaService.listaPesquisa(value).pipe(finalize(() => this.carregando = false))))
      .subscribe(pessoas => this.pessoas = pessoas);
  }

  ngOnInit() {
  }
  /**
   * Finaliza o Formulário de Informações do cliente
   */
  finalizarInformacoesDoPaciente($event, step: MatStepper): void {
    if (this.verificaPessoaValida() && this.verificaMedicoValido()) {
      const paciente: Pessoa = this.informationForm.get('pessoa').value;
      const medico: Medico = this.informationForm.get('medico').value;
      this.formulario.paciente = paciente.codigo;
      this.formulario.medico = medico.codigo;
      step.next;
    }
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
}
