import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { OfferService } from './offer.service';
import { Offer } from './offer.model';

import { User } from '../User/user.model';

import {HttpClient} from "../HttpClient/HttpClient";
import {SigninService} from '../signin/signin.service'


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
      characteristicList : []
  };

  constructor(private router:Router, activatedRoute: ActivatedRoute, private offerService: OfferService){}

  createOffer(){
    this.offerService.createOffer(this.formData).subscribe(
        Offer => {
          this.formData = Offer
          this.router.navigate(['user']);
        }
    );
  }
}
