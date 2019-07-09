import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Inject } from '@angular/core';

import { ErrorsService } from '../errors/errors.service';
import { AbstractReceitaService } from './abstract-receita.service';
import { ReceitaFactoryService } from './receita-factory.service';
import { FormularioReceita } from './formulario-receita.model';
import { Observable } from 'rxjs';

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

    public nova(receita: FormularioReceita): Observable<Object> {
        return this._receitaService.nova(receita);
    }

}
