import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import {User} from './user.model'
import {UserService} from './user.service'
import {SigninService} from '../signin/signin.service'

 
@Component({
  selector: 'editUser',
  templateUrl: './edit_user.component.html'
})

export class EditUserComponent {

   user: User = {
        id: null,
        name: "",
        firstLastName: "",
        secondLastName: "",
        email: "",
        phone: null,
        pass: "",
        description: "",
        admin: false,
        roles: [''],
        offers: [],
        reviews: []
  };

  formData: User = {
    id: null,
    name: "",
    firstLastName: "",
    secondLastName: "",
    email: "",
    phone: 0,
    pass: "",
    description: "",
  }
  private _isValid = {
    name:false,
    firstLastName: false,
    secondLastName: false,
    email: false,
    phone: false,
    userPassword: false,
    repeatUserPassword: false
  }

  image:any;

  constructor(private router:Router,private activatedRoute:ActivatedRoute,private userService:UserService,private signInService:SigninService){
    let id = activatedRoute.snapshot.params['id'];
    this.getUser(id);
  }

  getUser(id) {
        this.userService.getUser(id).subscribe(
            userDetail => this.formData = userDetail
        );
    }

  selectFile($event) {
    this.image = $event.target.files[0];
    console.log("Selected file: " + this.image.name + " type:" + this.image.type + " size:" + this.image.size);
  }

  editUser() {
    this.userService.updateUser(this.formData.id, this.formData).subscribe(
      response => {
        this.user = response,
        this.updatePhoto(this.user.id);
          this.signInService.logIn(this.formData.email, this.formData.pass).subscribe(
            response => {
              this.router.navigate(['user', this.formData.id]);
            },
            error => console.log("Error en edit User")
          );
      })
    this.router.navigate(['user', this.formData.id]);
}


  updatePhoto(id: number){
        let formData = new FormData();
        formData.append('file', this.image);
        this.userService.setUserPhoto(id, formData).subscribe(
            error => console.error(error)
        )
    }
  
    isValid() {
    return this._isValid.name &&
      this._isValid.userPassword &&
      this._isValid.repeatUserPassword &&
      this._isValid.email &&
      this._isValid.firstLastName &&
      this._isValid.phone &&
      this._isValid.secondLastName 

  }

  val1(value: String) {
    return value.length > 4;
  }
  
  valName(value: String) {
    return value.length > 0;
  }
}
