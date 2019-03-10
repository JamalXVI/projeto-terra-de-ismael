export class Pessoa {
    nome: string;
    sobrenome: string;
    cpf: string;
    ativo: true;
    constructor(obj?: any){
        this.nome = obj && obj.nome || "";
        this.sobrenome = obj && obj.sobrenome || "";
        this.cpf = obj && obj.cpf || "";
        this.ativo =  obj && obj.ativo || false;
    }
}