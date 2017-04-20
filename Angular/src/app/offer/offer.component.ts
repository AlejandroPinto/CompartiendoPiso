import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { OfferService } from './offer.service';
import { Offer } from './offer.model';

import { Headers, RequestOptions, Response } from '@angular/http';

import 'rxjs/add/operator/map';
import {HttpClient} from "../HttpClient/HttpClient";
import {SigninService} from '../signin/signin.service'


@Component({
  selector: 'offer',
  templateUrl: './offer.component.html'
})

export class OfferComponent {

  offer: Offer;

  constructor(private router:Router, activatedRoute: ActivatedRoute, private offerService: OfferService){
      let id = activatedRoute.snapshot.params['id'];
      this.getOfferId(id);   
  }

   getOfferId(id : number) {
        this.offerService.getOffer(id).subscribe(
            OfferDetail => this.offer = OfferDetail);
    }


  // constructor(private FilmsService: OfferService, private http: HttpClient, private loginService:SigninService) {

  // }

  // getAllOffers() {
  //   this.OfferService.getOffers().subscribe(
  //     filmsArray => { this.offersArray = filmsArray },
  //     error => console.log(error));
  // }

  // isEmpty(){
  //   if (this.offersArray.length===0){
  //     return true;
  //   }
  //   else return false;
  // }

 }
