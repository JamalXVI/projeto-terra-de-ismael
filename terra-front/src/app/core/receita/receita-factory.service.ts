import { Injectable } from '@angular/core';

import { environment } from '../../../environments/environment.prod';
import { ReceitaMockService } from './receita-mock.service';
import { ReceitaWebService } from './receita-web.service';
import { AbstractReceitaService } from './abstract-receita.service';

@Injectable({ providedIn: 'root' })
export class ReceitaFactoryService {
    constructor(
        private mock: ReceitaMockService,
        private web: ReceitaWebService
    ) { }
    getService(): AbstractReceitaService {
        if (environment.mock) {
            return this.mock;
        }
        return this.web;
    }
}
