export class MedicamentoPrincipioAtivo {
    id: number;
    proporcao: number;
    nome: string;

    constructor(obj?: any) {
        this.id = obj && obj.id || 0;
        this.proporcao = obj && obj.proporcao || 0;
        this.nome = obj && obj.nome || "";

    }
}