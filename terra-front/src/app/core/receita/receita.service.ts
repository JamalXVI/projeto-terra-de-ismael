import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Inject } from '@angular/core';

import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

import { ErrorsService } from '../errors/errors.service';
import { ElementoDaListaDto } from '../models/elemento-da-lista-dto.model';
import { AbstractReceitaService } from './abstract-receita.service';
import { ReceitaFactoryService } from './receita-factory.service';

@Inject({ providedIn: 'root' })
export class ReceitaService extends AbstractReceitaService {
    private _receitaService: AbstractReceitaService;
    constructor(
        protected router: Router,
        protected http: HttpClient,
        protected errorService: ErrorsService,
        protected receitaFactory: ReceitaFactoryService
    ) {
        super(router, http, errorService);
        this._receitaService = receitaFactory.getService();
    }

    public nova(): Observable<ElementoDaListaDto[]> {
        return this._receitaService.nova();
    }

}
