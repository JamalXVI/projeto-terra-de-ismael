import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class ErrorsService {
    private message: string;
    constructor() {

    }
    public setMessage(message: string) {
        this.message = message;
    }
    public getMessage(): string {
        return this.message;
    }
}
