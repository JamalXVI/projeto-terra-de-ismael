import { UserRole } from './user-role.enum';

export class User {
    public id: Number;
    public usuario: String;
    public nome: String;
    public sobrenome: String;
    public senha: String;
    public role: UserRole;

    constructor(obj?: any) {
        this.id = obj && obj.id || 0;
        this.usuario = obj && obj.usuario || '';
        this.nome = obj && obj.nome || '';
        this.sobrenome = obj && obj.sobrenome || '';
        this.senha = obj && obj.senha || '';
        this.role = obj && obj.role || 0;
    }
}
