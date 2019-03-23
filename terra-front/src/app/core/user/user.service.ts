import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs/internal/Observable';
import { map } from 'rxjs/operators';

import { User } from './user.model';
import { ErrorsService } from '../errors/errors.service';
import { UserUrl } from '../const/user-url.enum';
import { UserFactoryService } from './user-factory.service';
import { AbstractUserService } from './abstract-user.service';

@Injectable({ providedIn: 'root', })
export class UserService extends AbstractUserService {
    userService: AbstractUserService;
    constructor(
        protected router: Router,
        protected http: HttpClient,
        protected errorService: ErrorsService,
        protected userFactoryService: UserFactoryService) {
            super(router, http, errorService);
            this.userService = this.userFactoryService.getService();
    }
    getUsers(): Observable<User[]> {
        return this.userService.getUsers();
    }
    addUser(user: User) {
        //TODO: Implement Back-end Connection
    }
}
