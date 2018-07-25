import { HttpHeaders } from '@angular/common/http';



export const LOGIN_HEADERS = new HttpHeaders({
    'Accept': 'application/json',
    'Content-Type': 'application/x-www-form-urlencoded',
    'Access-Control-Allow-Origin': '*',
    'withCredentials': 'true'
});
