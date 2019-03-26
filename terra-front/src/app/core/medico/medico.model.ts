export class Medico {
    codigo: number;
    nome: string;
    sobrenome: string;
    cpf: string;
    crm: string;
    ativo: true;
    constructor(obj?: any) {
        this.nome = obj && obj.nome || "";
        this.sobrenome = obj && obj.sobrenome || "";
        this.cpf = obj && obj.cpf || "";
        this.crm = obj && obj.crm || "";
        this.ativo = obj && obj.ativo || false;
        this.codigo = obj && +obj.codigo || 0;
    }
    getNome(): string {
        return this.nome + " " + this.sobrenome;
    }

    toString(): string{
        return this.getNome();
    }
}