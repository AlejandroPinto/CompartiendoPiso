import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';

import { HttpClient } from '../HttpClient/httpClient';
import {User} from './user.model';


const BASE_URL = 'https://localhost:8443/api/user/';

@Injectable()
export class UserService{

	user: User;
	authCreds: string;

    constructor(private http: HttpClient){}

	setAuthHeaders(authCreds: string) {
    this.authCreds = authCreds;
	}

    getUser(id: number | string) {
		return this.http.get(BASE_URL + id)
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	getUserLogued() {
		return this.http.get(BASE_URL)
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	createUser(user: User){
		return this.http.post(BASE_URL,user)
		.map(response => response.json())
		.catch(error => this.handleError(error));
	}

	updateUser(id :number, user :User){
		return this.http.put(BASE_URL+id,user)
		.map(response => response.json())
		.catch(error => this.handleError(error));
	}

	setUserPhoto(id: number | string, formData: FormData){
		return this.http.put(BASE_URL + 'userPhoto/' + id , formData)
		.map(response => response.json())
		.catch(error => this.handleError(error));
	}

	// getPhotoUser(userName :string){
	// 	return this.http.get(BASE_URL+userName+'/photo')
	// 	.map(response => response.json())
	// 	.catch(error => this.handleError(error));
	// }

	//updated user avatar

    private handleError(error: any) {
		console.error(error);
		return Observable.throw("Server error (" + error.status + "): " + error.text())
	}
}