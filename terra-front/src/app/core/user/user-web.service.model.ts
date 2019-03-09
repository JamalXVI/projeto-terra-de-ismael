import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

import { User } from './user.model';
import { AbstractUserService } from './abstract-user-service.model';
import { Observable } from 'rxjs/internal/Observable';
import { UserUrl } from '../const/user-url.enum';
import { map } from 'rxjs/operators';
import { ErrorsService } from '../errors/errors.service';

@Injectable({ providedIn: 'root', })
export class UserWebService extends AbstractUserService {
    
    constructor(
        protected router: Router,
        protected http: HttpClient,
        protected errorService: ErrorsService){
        super(router, http, errorService);
    }
    getUsers(): Observable<User[]> {
        return this.http.get(UserUrl.GETLIST).pipe(map(users => <User[]>users));
    }
    addUser(user: User) {
        //TODO: Implement Back-end Connection
    }
}
