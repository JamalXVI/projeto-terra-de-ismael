import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';

import { MedicamentoService } from '../core/medicamento/medicamento.service';
import { ElementoDaListaDto } from '../core/models/elemento-da-lista-dto.model';
import { PessoaService } from '../core/pessoa/pessoa.service';

@Component({
  selector: 'app-gerador-receita',
  templateUrl: './gerador-receita.component.html',
  styleUrls: ['./gerador-receita.component.css'],
})
export class GeradorReceitaComponent implements OnInit {
  firstForm: FormGroup;
  medicamentos: ElementoDaListaDto[] = [];
  constructor(private _formBuilder: FormBuilder,
    private _medicamentoService: MedicamentoService,
    private _pessoaService: PessoaService) {
    this.firstForm = this._formBuilder.group({
      medicamento: new FormControl('', Validators.required)
    });
    this._medicamentoService.get().subscribe(medicamentos => {
      this.medicamentos = medicamentos;
    });

  }

  ngOnInit() {
  }

}
