export class EstadoToken{
    tokenDeAcesso: string;
    expiraEm: number;
    constructor(obj?: any){
        this.tokenDeAcesso = obj && obj.tokenDeAcesso || "";
        this.expiraEm = obj && obj.expiraEm || 0;
    }
}