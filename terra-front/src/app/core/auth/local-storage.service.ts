import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class LocalStorage {
    getItem(key: string, defaultValue?: any): any {
        if (!localStorage.getItem(key) || localStorage.getItem(key).toString() === 'undefined') {
            localStorage.setItem(key, defaultValue);
        }
        let retorno: any;
        try {
            retorno = JSON.parse(localStorage.getItem(key));
        } catch (error) {
            retorno = localStorage.getItem(key);
        }
        return retorno;
    }

    setItem(key: string, value: any): void {
        localStorage.setItem(key, value);
    }

    deleteItem(key: string): void {
        localStorage.removeItem(key);
    }
}
