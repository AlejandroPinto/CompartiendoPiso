import { Component,  OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Offer } from '../offer/offer.model';
import { User } from '../user/user.model';
import { AdminService } from './admin.service';
import { OfferService } from '../offer/offer.service';

@Component({
  selector: 'admin',
  templateUrl: './admin.component.html'
})

export class AdminComponent implements OnInit {
 
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
  
  private offers: Offer[] = [];

 
  constructor(private adminservice: AdminService, private router :Router, private offerservice: OfferService){}

  ngOnInit() {
    this.offerservice.getAllOffers().subscribe(
      offers => { this.offers = offers },
      error => console.log(error)
      );
  }
  
  deleteOfferAdmin(id: number) {
    let index;
    for (let i=0; i < this.offers.length; i++){
          if(this.offers[id].id === id ){
            index = i;
          }
    }
    this.offerservice.deleteOffer(id).subscribe(
      offers => { 
          this.offers.splice(index,1);
          console.log(this.offers);
    });
  }  

}