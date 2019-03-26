import { Router } from '@angular/router';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Inject } from '@angular/core';

import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ErrorsService } from '../errors/errors.service';
import { Medico } from './medico.model';
import { AbstractMedicoService } from './abstract-medico.service';
import { DEFAULT_SEARCH_TERMS_VALUE } from '../const/constants';
import { MedicoUrl } from './medico-url.enum';

@Inject({ providedIn: 'root' })
export class MedicoWebService extends AbstractMedicoService {
    constructor(
        protected router: Router,
        protected http: HttpClient,
        protected errorService: ErrorsService
    ) {
        super(router, http, errorService);
    }
    public listaPesquisa(pesquisa: string, limite?: number): Observable<Medico[]> {
        if(!limite){
            limite = DEFAULT_SEARCH_TERMS_VALUE;
        }
        const params: HttpParams = new HttpParams().set('pesquisa', pesquisa).set('limite', limite.toFixed(0));
        return this.http.get(MedicoUrl.GETSEARCHLIST, {params: params}).pipe(map(persons => (<any[]>persons).map(pes => new Medico(pes))));
    }
    public get(): Observable<Medico[]> {
        return this.http.get(MedicoUrl.GETLIST).pipe(map(persons => (<any[]>persons).map(pes => new Medico(pes))));
    }
}
