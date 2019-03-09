import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs/internal/Observable';

import { User } from './user.model';
import { AbstractUserService } from './abstract-user.service';
import { UserFactoryService } from './user-factory.service';
import { ErrorsService } from '../errors/errors.service';

@Injectable({ providedIn: 'root', })
export class UserService extends AbstractUserService {
    private service: AbstractUserService;
    constructor(
        protected router: Router,
        protected http: HttpClient,
        protected errorService: ErrorsService,
        private factory: UserFactoryService) {
        super(router, http, errorService);
        this.service = factory.getService();
    }
    getUsers(): Observable<User[]> {
        return this.service.getUsers();
    }
    addUser(user: User) {
       return this.service.addUser(user);
    }
}
