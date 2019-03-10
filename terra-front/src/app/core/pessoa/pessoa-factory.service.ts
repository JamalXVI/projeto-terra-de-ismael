import { Injectable } from '@angular/core';

import { environment } from '../../../environments/environment.prod';
import { PessoaMockService } from './pessoa-mock.service';
import { PessoaWebService } from './pessoa-web.service';
import { AbstractPessoaService } from './abstract-pessoa.service';

@Injectable({ providedIn: 'root' })
export class PessoaFactoryService {
    constructor(
        private mock: PessoaMockService,
        private web: PessoaWebService
    ) { }
    getService(): AbstractPessoaService {
        if (environment.mock) {
            return this.mock;
        }
        return this.web;
    }
}
