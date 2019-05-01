import { MedicamentoPrincipioAtivo } from './medicamento-principio-ativo.model';

export class ReceitaMedicamento{
    
    principioAtivos: MedicamentoPrincipioAtivo[];
    quantidade: number;
    tipo: number;

    constructor(obj?: any){
        this.principioAtivos = obj && obj.principioAtivos || [];
        this.quantidade = obj && obj.quantidade || 0;
        this.tipo = obj && obj.tipo || 0;
    }

}