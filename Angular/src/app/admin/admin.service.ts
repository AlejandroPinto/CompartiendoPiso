import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Router, ActivatedRoute } from '@angular/router';
import 'rxjs/Rx';

import { HttpClient } from '../HttpClient/httpClient';
import { SigninService } from '../signin/signin.service';


const BASE_URL = 'https://localhost:8443/api/admin';

@Injectable()
export class AdminService {

    constructor(private http: HttpClient, private signinservice: SigninService, private router: Router) { }

	getAdmin() {
        if(this.signinservice.isAdmin){
            return this.http.get(BASE_URL)
                .map(response => response.json())
                .catch(error => this.handleError(error));
        }else{
            this.router.navigate(['/signin']);
        }   
	}

	private handleError(error: any) {
		console.error(error);
		return Observable.throw("Server error (" + error.status + "): " + error.text())
	}
}