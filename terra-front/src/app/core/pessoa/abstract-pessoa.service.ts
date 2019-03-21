import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Inject } from '@angular/core';

import { Observable } from 'rxjs';

import { ErrorsService } from '../errors/errors.service';
import { Pessoa } from './pessoa.model';

@Inject({ providedIn: 'root' })
export abstract class AbstractPessoaService {
    constructor(
        protected router: Router,
        protected http: HttpClient,
        protected errorService: ErrorsService
    ) {
    }
    public abstract get(): Observable<Pessoa[]>;
    public abstract listaPesquisa(pesquisa: string, limite?: number): Observable<Pessoa[]>;
}
