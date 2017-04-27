import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';

import { HttpClient } from '../HttpClient/httpClient';
import {User} from '../User/user.model';


const BASE_URL = 'https://localhost:8443/api/user/';

@Injectable()
export class RegisterService{

	user: User;

    constructor(private http: HttpClient){}
    registerNewUser(data){
        let url=BASE_URL;
        console.log(data);
        return this.http.post(url, data).map(
            response=>response.json())
            .catch(error=>this.handleError(error));
    }
	
    private handleError(error: any) {
		console.error(error);
		return Observable.throw("Server error (" + error.status + "): " + error.text())
	}
}