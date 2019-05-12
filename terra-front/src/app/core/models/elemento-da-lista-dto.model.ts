export class ElementoDaListaDto {
    public id: number;
    public nome: string;
    constructor(obj?: any) {
        this.id = obj && +obj.id || 0;
        this.nome = obj && obj.nome || "";
    }

    public toString(){
        return this.nome;
    }
}
