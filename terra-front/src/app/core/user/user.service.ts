import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs/internal/Observable';
import { map } from 'rxjs/operators';

import { User } from './user.model';
import { ErrorsService } from '../errors/errors.service';
import { UserUrl } from '../const/user-url.enum';

@Injectable({ providedIn: 'root', })
export class UserService {
    constructor(
        protected router: Router,
        protected http: HttpClient,
        protected errorService: ErrorsService) {
    }
    getUsers(): Observable<User[]> {
        return this.http.get(UserUrl.GETLIST).pipe(map(users => <User[]>users));
    }
    addUser(user: User) {
        //TODO: Implement Back-end Connection
    }
}
