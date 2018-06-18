import { User } from './user.model';

export abstract class AbstractUserService {
    abstract getUsers(): User[];
    abstract addUser(user: User);
    abstract fillList(): User[];
}
