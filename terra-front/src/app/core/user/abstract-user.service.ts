
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs/internal/Observable';

import { User } from './user.model';
import { ErrorsService } from '../errors/errors.service';

export abstract class AbstractUserService {
    constructor(
        protected router: Router,
        protected http: HttpClient,
        protected errorService: ErrorsService
    ) {}
    abstract getUsers(): Observable<User[]>;
    abstract addUser(user: User);
}
