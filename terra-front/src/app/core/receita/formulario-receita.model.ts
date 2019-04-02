export class FormularioReceita {
    paciente: number;
    medico: number;
    dataReceita: string;
    constructor(obj?: any){
        this.paciente = obj && obj.paciente || 0;
        this.medico = obj && obj.medico || 0;
        this.dataReceita = obj && obj.dataReceita || "";
    }
}