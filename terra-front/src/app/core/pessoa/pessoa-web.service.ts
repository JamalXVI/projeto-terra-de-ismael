import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Inject } from '@angular/core';

import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ErrorsService } from '../errors/errors.service';
import { Pessoa } from './pessoa.model';
import { AbstractPessoaService } from './abstract-pessoa.service';
import { PessoaUrl } from '../const/pessoa-url.enum';

@Inject({ providedIn: 'root' })
export class PessoaWebService extends AbstractPessoaService {
    constructor(
        protected router: Router,
        protected http: HttpClient,
        protected errorService: ErrorsService
    ) {
        super(router, http, errorService);
    }
    public get(): Observable<Pessoa[]> {
        return this.http.get(PessoaUrl.GETLIST).pipe(map(persons => (<any[]>persons).map(pes => new Pessoa(pes))));
    }
}
