import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import {SigninService} from '../signin/signin.service';


@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html'
})


export class NavbarComponent {


  constructor(private signinService: SigninService ,private router:Router) {}

 }
