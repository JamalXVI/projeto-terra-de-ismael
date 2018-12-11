import { Injectable } from '@angular/core';

import { User } from './user.model';
import { AbstractUserService } from './abstract-user-service.model';
import { Observable } from 'rxjs/internal/Observable';
import { UserUrl } from '../const/user-url.enum';
import { map } from 'rxjs/operators';

@Injectable({ providedIn: 'root', })
export class UserWebService extends AbstractUserService {
    
    getUsers(): Observable<User[]> {
        return this.http.get(UserUrl.GETLIST).pipe(map(users => <User[]>users));
    }
    addUser(user: User) {
        //TODO: Implement Back-end Connection
    }
}
