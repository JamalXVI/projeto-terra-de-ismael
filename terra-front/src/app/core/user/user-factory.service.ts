import { Injectable } from '@angular/core';

import { UserMockService } from './user-mock.service';
import { UserWebService } from './user-web.service';
import { AbstractUserService } from './abstract-user.service';
import { environment } from '../../../environments/environment.prod';

@Injectable({ providedIn: 'root' })
export class UserFactoryService {
    constructor(
        private mock: UserMockService,
        private web: UserWebService
    ) { }
    getService(): AbstractUserService {
        if (environment.mock) {
            return this.mock;
        }
        return this.web;
    }
}
