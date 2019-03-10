import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Inject } from '@angular/core';

import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

import { ErrorsService } from '../errors/errors.service';
import { ElementoDaListaDto } from '../models/elemento-da-lista-dto.model';
import { MedicamentoUrl } from '../const/medicamento-url.enum';
import { AbstractMedicamentoService } from './abstract-medicamento.service';
import { MedicamentoFactoryService } from './medicamento-factory.service';

@Inject({ providedIn: 'root' })
export class MedicamentoService extends AbstractMedicamentoService {
    private _medicamentoService: AbstractMedicamentoService;
    constructor(
        protected router: Router,
        protected http: HttpClient,
        protected errorService: ErrorsService,
        protected medicamentoFactory: MedicamentoFactoryService
    ) {
        super(router, http, errorService);
        this._medicamentoService = medicamentoFactory.getService();
    }
    public get(): Observable<ElementoDaListaDto[]> {
        return this._medicamentoService.get();
    }
}
