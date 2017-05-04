import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import {Offer} from './offer.model'
import {OfferService} from './offer.service'
import {SigninService} from '../signin/signin.service'

import { Characteristic } from './characteristics.model';

@Component({
  selector: 'adModify',
  templateUrl: './adModify.component.html'
})

export class AdModifyComponent {

 //Variable aux para characteristics
  attributes: string[] = [];

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
    reviews: [],
    characteristics : []
  }
  
  private _isValid = {
    title: false,
    province: false,
    price: false,
    description: false,
    photos: false
  }


  

setInputType(type:string){
    this.offer.type = type;
  }

  setAttribute(attribute:string){
    if(this.attributes.indexOf(attribute)==-1){
      this.attributes.push(attribute);
    }else{
      this.attributes.splice(this.attributes.indexOf(attribute));
    }
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
      this.offer.characteristics = [];
      for(let characteristic of this.attributes){
        let characteristicToSave:Characteristic;
        characteristicToSave={name:characteristic,value:true};
        this.offer.characteristics.push(characteristicToSave);
     }
      console.log(this.offer.reviews);
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

  isValid() {
    return this._isValid.title &&
    this._isValid.description && 
    this._isValid.photos && 
    this._isValid.price && 
    this._isValid.province
  }

  val1(value: String) {
    return value.length > 4;
  }
  val2(value: number) {
    return value > 0;
  }
  valPhotos(value: string) {
    console.log(value !== "");
    return value !== "";
  }
}