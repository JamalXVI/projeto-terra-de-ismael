import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Inject } from '@angular/core';

import { Observable, of } from 'rxjs';

import { ErrorsService } from '../errors/errors.service';
import { ElementoDaListaDto } from '../models/elemento-da-lista-dto.model';
import { AbstractMedicamentoService } from './abstract-medicamento.service';

@Inject({ providedIn: 'root' })
export class MedicamentoMockService extends AbstractMedicamentoService {
    constructor(
        protected router: Router,
        protected http: HttpClient,
        protected errorService: ErrorsService
    ) {
        super(router, http, errorService);
    }
    public get(): Observable<ElementoDaListaDto[]> {
        return this._fillList();
    }
    public getDetails(id: number): Observable<ElementoDaListaDto[]> {
        return this._fillList();
    }
    private _fillList(): Observable<ElementoDaListaDto[]>{
        return of([new ElementoDaListaDto({id: 0, nome:'Cápsula'})]);
    }
}
