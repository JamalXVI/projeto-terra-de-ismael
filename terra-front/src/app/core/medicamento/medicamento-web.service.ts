import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Inject } from '@angular/core';

import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

import { ErrorsService } from '../errors/errors.service';
import { ElementoDaListaDto } from '../models/elemento-da-lista-dto.model';
import { MedicamentoUrl } from '../const/medicamento-url.enum';
import { AbstractMedicamentoService } from './abstract-medicamento.service';

@Inject({ providedIn: 'root' })
export class MedicamentoWebService extends AbstractMedicamentoService {
    constructor(
        protected router: Router,
        protected http: HttpClient,
        protected errorService: ErrorsService
    ) {
        super(router, http, errorService);
    }
    public get(): Observable<ElementoDaListaDto[]> {
        return this.http.get(MedicamentoUrl.GETLIST).pipe(map(medicines => (<any[]>medicines).map(med => new ElementoDaListaDto(med))));
    }
}
