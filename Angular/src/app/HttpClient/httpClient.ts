import { Injectable, OnInit } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { SigninService } from "../signin/signin.service";
import { SessionData } from "../signin/sessionData.model";
import { User } from '../user/user.model';


@Injectable()
export class HttpClient {

  public sessionData: SessionData;

  private logoutURI = "https://localhost:8443/api/logOut";

  constructor(private http: Http) {
    this.sessionData = new SessionData();
  }

  generateHeaders() {
    let headers = new Headers();
    if (this.sessionData.amILogged)
      headers.append('Authorization', this.sessionData.authToken());
    return headers;
  }

  get(url) {
    console.log("HttpClient pre GET")
    console.log(this.sessionData.authToken());
    console.log(this.http.get(url,{
      headers: this.generateHeaders()
    }))
    return this.http.get(url,{
      headers: this.generateHeaders()
    });
}

post(url, data) {
  return this.http.post(url, data, {
    headers: this.generateHeaders()
  });
}

put(url, data) {
  return this.http.put(url, data, {
    headers: this.generateHeaders()
  });
}

delete (url){
  return this.http.delete(url, {
    headers: this.generateHeaders()
  });
}
setUser(u:User){
  this.sessionData.setUserLogged(u);
  this.sessionData.setAmIAdmin(this.sessionData.getUserLogged().roles.indexOf("ROLE_ADMIN") > -1);
  this.sessionData.setAmILogged(true);
  this.sessionData.saveData();
}

  logOut(){
    return this.get(this.logoutURI).map(
      response => {
        this.sessionData.reset();
      },
      error => console.log(error)
    );
  }
}
