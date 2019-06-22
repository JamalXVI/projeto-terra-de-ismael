import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Inject } from '@angular/core';

import { ErrorsService } from '../errors/errors.service';
import { FormularioReceita } from './formulario-receita.model';

@Inject({ providedIn: 'root' })
export abstract class AbstractReceitaService {
    constructor(
        protected router: Router,
        protected http: HttpClient,
        protected errorService: ErrorsService
    ) {
    }
    public abstract nova(receita: FormularioReceita): void;
}
