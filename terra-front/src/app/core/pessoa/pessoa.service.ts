import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Inject } from '@angular/core';

import { Observable } from 'rxjs';

import { ErrorsService } from '../errors/errors.service';
import { Pessoa } from './pessoa.model';
import { AbstractPessoaService } from './abstract-pessoa.service';
import { PessoaFactoryService } from './pessoa-factory.service';

@Inject({ providedIn: 'root' })
export class PessoaService extends AbstractPessoaService {
    pessoaService: AbstractPessoaService;
    constructor(
        protected router: Router,
        protected http: HttpClient,
        protected errorService: ErrorsService,
        protected pessoaFactoryService: PessoaFactoryService
    ) {
        super(router, http, errorService);
        this.pessoaService = pessoaFactoryService.getService();
    }
    public get(): Observable<Pessoa[]> {
        return this.pessoaService.get();
    }
}
