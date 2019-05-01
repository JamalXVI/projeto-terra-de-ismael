import { Router } from '@angular/router';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Inject } from '@angular/core';

import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

import { ErrorsService } from '../errors/errors.service';
import { ElementoDaListaDto } from '../models/elemento-da-lista-dto.model';
import { MedicamentoUrl } from '../const/medicamento-url.enum';
import { AbstractMedicamentoService } from './abstract-medicamento.service';
import { DEFAULT_SEARCH_TERMS_VALUE } from '../const/constants';

@Inject({ providedIn: 'root' })
export class MedicamentoWebService extends AbstractMedicamentoService {
    constructor(
        protected router: Router,
        protected http: HttpClient,
        protected errorService: ErrorsService
    ) {
        super(router, http, errorService);
    }
    public get(): Observable<ElementoDaListaDto[]> {
        return this.http.get(MedicamentoUrl.GETLIST).pipe(map(medicines => (<any[]>medicines).map(med => new ElementoDaListaDto(med))));
    }
    public getPrincipioAtivo(pesquisa: string, limite?: number): Observable<ElementoDaListaDto[]> {
        if(!limite){
            limite = DEFAULT_SEARCH_TERMS_VALUE;
        }
        const params: HttpParams = new HttpParams().set('pesquisa', pesquisa).set('limite', limite.toFixed(0));
        return this.http.get(MedicamentoUrl.GETACTIVE, {params: params}).pipe(map(medicines => (<any[]>medicines).map(med => new ElementoDaListaDto(med))));
    }
}
