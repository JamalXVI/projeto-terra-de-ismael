import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Inject } from '@angular/core';

import { Observable } from 'rxjs';

import { ErrorsService } from '../errors/errors.service';
import { ElementoDaListaDto } from '../models/elemento-da-lista-dto.model';

@Inject({ providedIn: 'root' })
export abstract class AbstractMedicamentoService {
    constructor(
        protected router: Router,
        protected http: HttpClient,
        protected errorService: ErrorsService
    ) {
    }
    public abstract get(): Observable<ElementoDaListaDto[]>;
}
