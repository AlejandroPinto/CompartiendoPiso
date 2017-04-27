import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from '../User/user.model'
import { RegisterService } from './register.service'

@Component({
  selector: 'register',
  templateUrl: './register.component.html'
})

export class RegisterComponent {
  formData: User = {
    name: "",
    firstLastName: "",
    secondLastName: "",
    email: "",
    phone: 0,
    pass: "",
    description: "",
    admin: false
  }

  private _isValid = {
    userPassword: false,
    repeatUserPassword: false,
    email: false,
    name:false,
    firstLastName:false,
    secondLastName:false,
    description:false,
  }
    private emailRegex = new RegExp(/[^\s@]+@[^\s@]+\.[^\s@]+/);




  constructor(private registerService: RegisterService, private router: Router) { }

  ngOnInit() {

  }
  register() {
    this.registerService.registerNewUser(this.formData).subscribe(
      response => 
      this.router.navigate(['']),
      error=>console.log(error)
    )

  }


  isValid() {
    return this._isValid.name &&
      this._isValid.userPassword &&
      this._isValid.repeatUserPassword &&
      this._isValid.email &&
      this._isValid.firstLastName &&
      this._isValid.secondLastName &&
      this._isValid.description

  }
  val1(value: String) {
    return value.length > 6;
  }

  val2(value: string) {
    return this.emailRegex.test(value);
  }
}