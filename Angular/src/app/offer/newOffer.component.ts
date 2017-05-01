import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { OfferService } from './offer.service';
import { Offer } from './offer.model';

import { User } from '../User/user.model';

import {HttpClient} from "../HttpClient/HttpClient";
import {SigninService} from '../signin/signin.service'

import { Characteristic } from './characteristics.model';


@Component({
  selector: 'newOffer',
  templateUrl: './newOffer.component.html'
})

export class NewOfferComponent {
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

  //Variable aux para characteristics
  attributes: string[] = [];
  formData: Offer = {
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
      reviewList: [],
      characteristics : []
  };

  image: any;

  constructor(private router:Router, activatedRoute: ActivatedRoute, private offerService: OfferService){}

  setInputType(type:string){
    this.formData.type = type;
  }

  setAttribute(attribute:string){
    if(this.attributes.indexOf(attribute)==-1){
      this.attributes.push(attribute);
    }else{
      this.attributes.splice(this.attributes.indexOf(attribute));
    }
  }

  selectFile($event) {
    this.image = $event.target.files[0];
    console.log("Selected file: " + this.image.name + " type:" + this.image.type + " size:" + this.image.size);
  }

  createOffer(){
    for(let characteristic of this.attributes){
      let characteristicToSave:Characteristic;
      characteristicToSave={name:characteristic,value:true};
      this.formData.characteristics.push(characteristicToSave);
   }
    this.offerService.createOffer(this.formData).subscribe(
        Offer => {
          this.formData = Offer
          this.updatePhoto(this.formData.id);
          this.router.navigate(['user']);
        }
    );
  }

  updatePhoto(id: number){
         let formData = new FormData();
        formData.append('file', this.image);
        this.offerService.setOfferPhoto(id, formData).subscribe(
            error => console.error(error)
        )
    }
}
