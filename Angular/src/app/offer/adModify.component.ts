import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import {Offer} from './offer.model'
import {OfferService} from './offer.service'
import {SigninService} from '../signin/signin.service'

@Component({
  selector: 'adModify',
  templateUrl: './adModify.component.html'
})

export class AdModifyComponent {

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
    user: null,
    reviewList: [],
    characteristicList : []
  }

  constructor(private router:Router,private activatedRoute:ActivatedRoute,private offerService:OfferService,private signInService:SigninService){
    let id = activatedRoute.snapshot.params['id'];
    this.setOffer(id);
  }

  setOffer(id:number) {
        this.offerService.getOffer(id).subscribe(
            offerDetail => this.offer = offerDetail);
    }

    editOffer(){
    if(this.signInService.isLogged()){
      this.offerService.updateOffer(this.offer.id,this.offer).subscribe(
        response => {
          this.offer = response;
          //this.signInService.logoutService();
          //this.signInService.logIn(this.user.email,this.user.pass);
          this.router.navigate(['user']);
        }
      )
    }else{
      this.router.navigate(['/']);
    }
  }
}