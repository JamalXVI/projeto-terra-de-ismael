export class ElementoDaListaDto{
    private id: number;
    private nome: string;
    constructor(obj?: any){
        this.id = obj && obj.id || 0;
        this.nome = obj && obj.nome || "";
    }
}
