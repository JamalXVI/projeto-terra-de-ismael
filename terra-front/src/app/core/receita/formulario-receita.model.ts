export class FormularioReceita {
    paciente: number;
    medico: number;
    constructor(obj?: any){
        this.paciente = obj && obj.paciente || 0;
        this.medico = obj && obj.medico || 0;
    }
}