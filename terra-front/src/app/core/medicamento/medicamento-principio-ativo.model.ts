export class MedicamentoPrincipioAtivo {
    id: number;
    proporcao: number;

    constructor(obj?: any) {
        this.id = obj && obj.id;
        this.proporcao = obj && obj.proporcao;
    }
}