import { Injectable } from '@angular/core';

import { environment } from '../../../environments/environment.prod';
import { MedicamentoMockService } from './medicamento-mock.service';
import { MedicamentoWebService } from './medicamento-web.service';
import { AbstractMedicamentoService } from './abstract-medicamento.service';

@Injectable({ providedIn: 'root' })
export class MedicamentoFactoryService {
    constructor(
        private mock: MedicamentoMockService,
        private web: MedicamentoWebService
    ) { }
    getService(): AbstractMedicamentoService {
        if (environment.mock) {
            return this.mock;
        }
        return this.web;
    }
}
