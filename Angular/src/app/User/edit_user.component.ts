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
  };
  image:any;
  constructor(private router:Router,private activatedRoute:ActivatedRoute,private userService:UserService,private signInService:SigninService){
    let id = activatedRoute.snapshot.params['id'];
    this.user = signInService.getUser();
  }

  selectFile($event) {
    this.image = $event.target.files[0];
    console.log("Selected file: " + this.image.name + " type:" + this.image.type + " size:" + this.image.size);
  }

  editUser(){
    if(this.signInService.isLogged()){
      this.userService.updateUser(this.user.id,this.user).subscribe(
        response => {
          this.user = response;
          this.updatePhoto(this.user.id);
          this.signInService.logoutService();
          this.signInService.logIn(this.user.email,this.user.pass);
          this.router.navigate(['user']);
        }
      )
    }else{
      this.router.navigate(['/']);
    }
  }

  updatePhoto(id: number){
        let formData = new FormData();
        formData.append('file', this.image);
        this.userService.setUserPhoto(id, formData).subscribe(
            error => console.error(error)
        )
    }

}
