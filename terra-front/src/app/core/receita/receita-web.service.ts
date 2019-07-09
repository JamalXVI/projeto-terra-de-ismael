import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Inject } from '@angular/core';

import { ErrorsService } from '../errors/errors.service';
import { ReceitaUrl } from '../const/receita-url.enum';
import { AbstractReceitaService } from './abstract-receita.service';
import { FormularioReceita } from './formulario-receita.model';
import { Observable } from 'rxjs';

@Inject({ providedIn: 'root' })
export class ReceitaWebService extends AbstractReceitaService {
    constructor(
        protected router: Router,
        protected http: HttpClient,
        protected errorService: ErrorsService
    ) {
        super(router, http, errorService);
    }
    public nova(receita:FormularioReceita): Observable<Object> {
        return this.http.post(ReceitaUrl.NOVA, receita,{  responseType: 'blob'});
    }
}
