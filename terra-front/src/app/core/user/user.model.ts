import { UserRole } from './user-role.enum';

export class User {
    public id: Number;
    public user: String;
    public name: String;
    public password: String;
    public role: UserRole;

    constructor(obj?: any) {
        this.id = obj && obj.id || 0;
        this.user = obj && obj.user || '';
        this.name = obj && obj.name || '';
        this.password = obj && obj.password || '';
        this.role = obj && obj.role || 0;
    }
}
