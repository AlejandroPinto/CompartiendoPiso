import {Injectable, OnInit} from '@angular/core';
import {Http, Headers} from '@angular/http';
import {SigninService} from "../signin/signin.service";
import {SessionData} from "../signin/sessionData.model";

@Injectable()
export class HttpClient{

    public sessionData: SessionData;

  constructor(private http: Http) {
    this.sessionData = { isLogged: false, isAdmin: false, userLogged: {
        id: 0,
        name: "",
        firstLastName: "",
        secondLastName: "",
        email: "",
        phone: 0,
        pass: "",
        description: "",
        admin: false,
        roles: [''],
        offers: [],
        reviewList: []
    }, 
    authToken: ""
    };
  }

  generateHeaders(){
    let headers = new Headers();
    if(this.sessionData.isLogged)
      headers.append('Authorization', this.sessionData.authToken);
    return headers;
  }

  get(url) {
    return this.http.get(url, {
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

  delete(url){
    return this.http.delete(url, {
      headers: this.generateHeaders()
    });
  }
}
