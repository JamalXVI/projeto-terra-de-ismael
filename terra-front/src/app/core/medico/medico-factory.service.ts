import { Injectable } from '@angular/core';

import { environment } from '../../../environments/environment.prod';
import { MedicoMockService } from './medico-mock.service';
import { MedicoWebService } from './medico-web.service';
import { AbstractMedicoService } from './abstract-medico.service';

@Injectable({ providedIn: 'root' })
export class MedicoFactoryService {
    constructor(
        private mock: MedicoMockService,
        private web: MedicoWebService
    ) { }
    getService(): AbstractMedicoService {
        if (environment.mock) {
            return this.mock;
        }
        return this.web;
    }
}
