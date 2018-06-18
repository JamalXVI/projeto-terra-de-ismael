import { Injectable } from '@angular/core';

import { environment } from '../../../environments/environment';

import { AuthMockService } from './auth-mock.service';
import { AuthWebService } from './auth-web.service';
import { AbstractAuthService } from './abstract-auth-service.model';

@Injectable({ providedIn: 'root' })
export class AuthFactoryService {
    constructor(
        private mock: AuthMockService,
        private web: AuthWebService
    ) { }
    getService(): AbstractAuthService {
        if (environment.mock) {
            return this.mock;
        }
        return this.web;
    }
}
