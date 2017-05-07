import { Component, NgModule } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { OfferService } from './offer.service';
import { UserService } from '../user/user.service';
import { Offer } from './offer.model';
import { User } from '../user/user.model';
import { Review } from '../review/review.model';

import { Headers, RequestOptions, Response } from '@angular/http';

import 'rxjs/add/operator/map';
import {HttpClient} from "../HttpClient/HttpClient";
import {SigninService} from '../signin/signin.service'


@Component({
  selector: 'offer',
  templateUrl: './offer.component.html'
})

export class OfferComponent {

  userDetail: User = {
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
      id: null,
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
      user: this.userDetail,
      reviews: [],
      characteristics : []
  };

  review : Review = {
    valoration: 0,
    comment: "",
    date : new Date().toDateString(), 
    offer: this.offer,
    user: this.userDetail,
  }

  reviewValoration: number;
  reviewComment : string="";
  offerID: number;
 
  formData: Review = {
    valoration: 0,
    comment: ""
  };

   private _isValid = {
    valoration: false,
    comment: false
  }

  isValid() {
    return this._isValid.valoration &&
      this._isValid.comment
  }

  val1(value: String) {
    return value.length > 1;
  }

  constructor(private router:Router, activatedRoute: ActivatedRoute, private offerService: OfferService,
              private userService: UserService, private signinService: SigninService){
      let id = activatedRoute.snapshot.params['id'];
      this.getOfferId(id);   
      this.getUserId(id);
      this.offerID = id;
  }

  setValoration(rate:number){
    this.formData.valoration=rate;
  }

  setInputType(type:string){
    this.formData.offer.type;
  }
  
   getOfferId(id : number) {
        this.offerService.getOffer(id).subscribe(
            response => {
              this.offer = response;
            }
        );       
    }

    getUserId(id:number) {
        this.userService.getUser(id).subscribe(
            userDetail => this.userDetail = userDetail);
    }

    addReview() {
      this.offerService.addReviews(this.offerID, this.formData).subscribe(
        review =>  {
          this.offer.reviews=review.reviews;
        }
      );
    }

 }