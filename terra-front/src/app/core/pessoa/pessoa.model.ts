export class Pessoa {
    codigo: number;
    nome: string;
    sobrenome: string;
    cpf: string;
    ativo: true;
    constructor(obj?: any) {
        this.nome = obj && obj.nome || "";
        this.sobrenome = obj && obj.sobrenome || "";
        this.cpf = obj && obj.cpf || "";
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