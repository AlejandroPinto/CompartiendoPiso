import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from '../user/user.model';
import {SigninService} from '../signin/signin.service'


@Component({
  selector: 'header',
  templateUrl: './header.component.html'
})

export class HeaderComponent { 

constructor(private signinService: SigninService ,private router:Router) {}

  user: User;

  logOut(){
    this.signinService.logout().subscribe()
  }

  
}
