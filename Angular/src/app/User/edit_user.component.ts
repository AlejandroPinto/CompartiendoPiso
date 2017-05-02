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
        reviews: []
  };

  constructor(private router:Router,private activatedRoute:ActivatedRoute,private userService:UserService,private signInService:SigninService){
    let id = activatedRoute.snapshot.params['id'];
    this.user = signInService.getUser();
  }

  

  editUser(){
    if(this.signInService.isLogged()){
      this.userService.updateUser(this.user.id,this.user).subscribe(
        response => {
          this.user = response;
          this.signInService.logoutService();
          this.signInService.logIn(this.user.email,this.user.pass);
          this.router.navigate(['user']);
        }
      )
    }else{
      this.router.navigate(['/']);
    }
  }

}
