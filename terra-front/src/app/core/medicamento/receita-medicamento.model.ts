import { MedicamentoPrincipioAtivo } from './medicamento-principio-ativo.model';

export class ReceitaMedicamento{
    
    id: number;
    principioAtivos: MedicamentoPrincipioAtivo[];
    quantidade: number;
    peso: number;
    posologia: string;
    validade: string;
    tipo: number;
    nome: string;

    constructor(obj?: any){
        this.id = obj && obj.id || 0;
        this.principioAtivos = obj && obj.principioAtivos || [];
        this.quantidade = obj && obj.quantidade || 0;
        this.peso = obj && obj.peso || 0;
        this.tipo = obj && obj.tipo || 0;
        this.posologia = obj && obj.posologia || "";
        this.tipo = obj && obj.tipo || 0;
        this.nome = obj && obj.nome || "";
        this.validade = obj && obj.validade || "";
    }

}