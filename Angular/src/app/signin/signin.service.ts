import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Router } from '@angular/router';
import { Headers } from '@angular/http';

import { User } from '../user/user.model';
import { UserService } from '../user/user.service';
import { HttpClient } from '../HttpClient/httpClient';

import 'rxjs/Rx';

 

@Injectable()
export class SigninService {

    //userLogged: User;
    //authCreds: string;
    //isLogged = false;
    //isAdmin = false;
    BASE_URL_Login = "https://localhost:8443/api/logIn";
    BASE_URL_Logout = 'https://localhost:8443/api/logOut';

    constructor(private http: HttpClient, private userService: UserService) {
    }

    private generateAuthString(username: String, password: String) {
        return "Basic " + btoa(username + ":" + password);
    }
    private updateUser(username: string) {
        console.log("Entrando aaaaaa")
        return this.userService.getUser(username).map(
            user => {
                this.http.setUser(user);
                return user;
            }
        );
    }

    logIn(username: string, password: string) {
       
        this.http.sessionData.setAuthToken(this.generateAuthString(username, password));
        this.http.sessionData.setAmILogged(true);

       return this.http.get("https://localhost:8443/api/logIn").map(
          response => this.updateUser(username).subscribe())
          .catch(error =>{ console.log("Error");return this.loginFailed(error)});
    }
    private loginFailed(error: any){
      this.http.sessionData.setAmILogged(false);
      return Observable.throw("Server error (" + error.status + "): " + error.text())
    }

    //   logOut(){
    //         console.log("logOut");
    //         return this.http.get(BASE_URL_Logout)
    //         .map(
    //             response => {
    //                 this.http.sessionData.userLogged = null;
    //                 console.log("logOut");
    //                 console.log(this.http.sessionData.userLogged);
    //                 this.http.sessionData.isLogged = false;
    //                 console.log("logOut");
    //                 console.log( this.http.sessionData.isLogged);
    //             }
    //         );
    //     }

    public logoutService() {

        if (!this.isLogged())
            return;

        return this.http.logOut();
    }

    public isLogged() {
        return this.http.sessionData.amILogged();
    }

    public isAdmin() {
        return this.isLogged() && this.http.sessionData.amIAdmin();
    }

    public getUser() {
        return this.http.sessionData.getUserLogged();
    }

    public forceUpdateUser() {
        return this.updateUser(this.http.sessionData.getUserLogged().email);
    }
    public updateUserLogged(user:User){
        this.http.setUser(user);
    }
}