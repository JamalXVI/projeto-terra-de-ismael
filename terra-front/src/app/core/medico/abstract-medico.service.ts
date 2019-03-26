import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Inject } from '@angular/core';

import { Observable } from 'rxjs';

import { ErrorsService } from '../errors/errors.service';
import { Medico } from './medico.model';

@Inject({ providedIn: 'root' })
export abstract class AbstractMedicoService {
    constructor(
        protected router: Router,
        protected http: HttpClient,
        protected errorService: ErrorsService
    ) {
    }
    public abstract get(): Observable<Medico[]>;
    public abstract listaPesquisa(pesquisa: string, limite?: number): Observable<Medico[]>;
}
