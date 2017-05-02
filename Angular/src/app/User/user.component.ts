import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { UserService } from '../user/user.service';
import {SigninService} from '../signin/signin.service'
import { User } from './user.model';
import { Offer } from '../offer/offer.model';
import { OfferService } from '../offer/offer.service';
import {Characteristic } from '../offer/characteristics.model';
import { Review } from '../review/review.model';


@Component({
  selector: 'user',
  templateUrl: './user.component.html'
})

export class UserComponent {

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

  offer: Offer = {
      id: 0,
	  title: "",
	  price: 0,
      description: "",
      province: "",
      location: "",
      neighborhood: "",
      area: 0,
      bathroom: 0,
      room: 0,
      type : "",
      user: this.user,
      reviews: [],
      characteristics : []
  };


 constructor(private router: Router, activatedRoute: ActivatedRoute, private userService:UserService, private signInService:SigninService,private offerService:OfferService) {
        let id = activatedRoute.snapshot.params['id'];
        if(id != undefined){
          this.getUserId(id);
        }else{
          this.getUser();
        }
  }

   getUserId(id:number) {
        this.userService.getUser(id).subscribe(
            userDetail => this.user = userDetail);
    }

  
  deleteOffer(id: number) {
    let index;
    for (let i=0; i < this.user.offers.length; i++){
          if(this.user.offers[i].id === id ){
            index = i;
          }
    }
    this.offerService.deleteOffer(this.user.offers[index].id).subscribe(
      offers => { 
          this.user.offers.splice(index,1);
          console.log(this.user.offers);
    });
  } 

    getUser() {
        this.userService.getUserLogued().subscribe(
            userDetail => this.user = userDetail);
    }

    isOwner(){
      let userOwner: boolean = (this.user.id == this.signInService.getUser().id);
      return userOwner;
    }

    isEmpty(){
    if (this.user.offers.length===0){
      return true;
    }
    else return false;
  }
}