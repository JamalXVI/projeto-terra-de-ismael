import { Injectable } from '@angular/core';

import { User } from './user.model';
import { AbstractUserService } from './abstract-user-service.model';
import { UserFactoryService } from './user-factory.service';

@Injectable({ providedIn: 'root', })
export class UserService extends AbstractUserService {
    private service: AbstractUserService;
    constructor(private factory: UserFactoryService) {
        super();
        this.service = factory.getService();
    }
    getUsers(): User[] {
        return this.service.getUsers();
    }
    addUser(user: User) {
       return this.service.addUser(user);
    }
    fillList(): User[] {
        return this.service.fillList();
    }
}
