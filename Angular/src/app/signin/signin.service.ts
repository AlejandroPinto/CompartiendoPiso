import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Router } from '@angular/router';
import { Http, Headers } from '@angular/http';

import { User } from '../user/user.model';
import { UserService } from '../user/user.service';
import { HttpClient } from '../HttpClient/httpClient';

import 'rxjs/Rx';

const BASE_URL_Login = 'https://localhost:8443/api/logIn';
const BASE_URL_Logout = 'https://localhost:8443/api/logOut';

@Injectable()
export class SigninService {

  //userLogged: User;
  //authCreds: string;
  //isLogged = false;
  //isAdmin = false;

  constructor(private http: HttpClient, private userService: UserService) {
  }

  private generateAuthString(username: String, password: String) {
        return "Basic " + btoa(username + ":" + password);
  }

  logIn(username: string, password: string) {
    console.log("logIn");
    this.http.sessionData.authToken = this.generateAuthString(username,password);
    this.http.sessionData.isLogged = true;

    return this.http.get(BASE_URL_Login).map(
        response => {
          this.userService.getUser(username).subscribe(
            user => {
              this.http.sessionData.userLogged = user;
              this.http.sessionData.isLogged = true;
              // if(this.http.sessionData.userLogged.roles.indexOf("ADMIN") > -1){
              //   this.http.sessionData.isAdmin = true;
              // }
            },
            error =>{
              console.log(error)
              this.http.sessionData.isLogged = false;
            } 
            
          );
      })
      .catch(error => Observable.throw('Server error'));
  }

//   // logOut() {
//   //   console.log("logOut");
//   //   // let headers: Headers = new Headers();
//   //   // headers.append('Authorization', 'Basic ' + this.authCreds);
//   //   return this.http.get(BASE_URL + 'logOut')
//   //     .map(response => {
//   //       this.isLogged = false;
//   //       this.userLogged = null;
//   //       return true;
//   //     })
//   //     .catch(error => Observable.throw('Server error'));
//   // }

  logOut(){
        console.log("logOut");
        return this.http.get(BASE_URL_Logout)
        .map(
            response => {
                this.http.sessionData.userLogged = null;
                console.log("logOut");
                console.log(this.http.sessionData.userLogged);
                this.http.sessionData.isLogged = false;
                console.log("logOut");
                console.log( this.http.sessionData.isLogged);
            }
        );
    }

    public IsLogged() {
        return this.http.sessionData.isLogged;
    }

    public IsAdmin() {
        return this.IsLogged() && this.http.sessionData.isAdmin;
    }

    public getUser() {
        return this.http.sessionData.userLogged;
  }
}