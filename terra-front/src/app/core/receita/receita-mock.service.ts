import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Inject } from '@angular/core';

import { Observable, of } from 'rxjs';

import { ErrorsService } from '../errors/errors.service';
import { ElementoDaListaDto } from '../models/elemento-da-lista-dto.model';
import { AbstractReceitaService } from './abstract-receita.service';
import { FormularioReceita } from './formulario-receita.model';

@Inject({ providedIn: 'root' })
export class ReceitaMockService extends AbstractReceitaService {
    constructor(
        protected router: Router,
        protected http: HttpClient,
        protected errorService: ErrorsService
    ) {
        super(router, http, errorService);
    }
    public nova(receita: FormularioReceita): void {
    }
}
