import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Inject } from '@angular/core';

import { Observable } from 'rxjs';

import { ErrorsService } from '../errors/errors.service';
import { Medico } from './medico.model';
import { AbstractMedicoService } from './abstract-medico.service';
import { MedicoFactoryService } from './medico-factory.service';

@Inject({ providedIn: 'root' })
export class MedicoService extends AbstractMedicoService {
    medicoService: AbstractMedicoService;
    constructor(
        protected router: Router,
        protected http: HttpClient,
        protected errorService: ErrorsService,
        protected medicoFactoryService: MedicoFactoryService
    ) {
        super(router, http, errorService);
        this.medicoService = medicoFactoryService.getService();
    }
    public get(): Observable<Medico[]> {
        return this.medicoService.get();
    }
    public listaPesquisa(pesquisa: string, limite?: number): Observable<Medico[]> {
        return this.medicoService.listaPesquisa(pesquisa, limite);
    }
}
