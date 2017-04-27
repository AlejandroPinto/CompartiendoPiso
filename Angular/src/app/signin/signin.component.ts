import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from '../user/user.model';
import {SigninService} from '../signin/signin.service'

@Component({
  selector: 'signin',
  templateUrl: './signin.component.html'
})

export class SigninComponent {

  private userLogged:User;
  
  constructor(private signinService: SigninService, private router: Router) {
  }

  logInUser(email: string, pass: string) {
     this.signinService.logIn(email, pass).subscribe(
       user => {
         this.userLogged = user;
         this.router.navigate(['']);
       },
       error => console.log("Fail trying to login.")
     );
   }

   public logOutUser() {
        console.log("Cerrando sesion");
        this.signinService.logoutService();
    }
}