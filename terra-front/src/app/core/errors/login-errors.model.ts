export class LoginErrors {
    suceded: boolean;
    message: string;
    constructor(obj?: any) {
        this.suceded = obj && obj.suceded || false;
        this.message = obj && obj.message || '';
    }
}
