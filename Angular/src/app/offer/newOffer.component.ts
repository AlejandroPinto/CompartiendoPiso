import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Offer } from './offer.model';
import { Characteristic } from './characteristics.model';
import { User } from '../User/user.model';
import { OfferService} from './offer.service';

@Component({
  selector: 'newOffer',
  templateUrl: './newOffer.component.html'
})

export class NewOfferComponent { 
  
  usertest: User;
  title: string;
	price: number;
  description?: string;
  province: string;
  location?: string;
  neighborhood?: string;
  area?: number;
  bathroom?: number;
  rooms?: number;
  type : string;
  attributes: string[] = [];
  characteristics: Characteristic[] = [];
  service:OfferService;

setInputType(type:string){
  this.type = type;
}

setAttribute(attribute:string){
    if(this.attributes.indexOf(attribute)==-1){
      this.attributes.push(attribute);
    }else{
      this.attributes.splice(this.attributes.indexOf(attribute));
    }
}

addOffer(){

  this.usertest={
    name:"Adrian",
    firstLastName:"Martin",
    secondLastName:"Sanchez",
    email:"a@a.com",
    pass:"1234",
    phone:918115789, 
    offers:[]
  }


  let offerToSave:Offer;
  offerToSave = {
  title:this.title,
  price:this.price,
  description:this.description,
  province:this.province,
  location:this.location,
  neighborhood:this.neighborhood,
  area:this.area,
  bathroom:this.bathroom,
  room:this.rooms,
  type:this.type,
  user:this.usertest
  };
  
  for(let characteristic of this.attributes){
    let characteristicToSave:Characteristic;
    characteristicToSave={name:characteristic,value:true};
    this.characteristics.push(characteristicToSave);
  }

  offerToSave.characteristicList=this.characteristics;
  console.log(offerToSave);
  this.service.createOffer(offerToSave);

}
 
}
