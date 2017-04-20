import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { OfferService } from './offer.service';
import { Offer } from './offer.model';
import { SigninService } from '../signin/signin.service';

@Component({
  selector: 'offers'
})
export class OffersComponent implements OnInit {

  offersPage: number;
  offersPageActual: number;
  moreOffersButtonText: string;

  offers: Offer[];

  constructor(private router: Router, private service: OfferService, private sessionService: SigninService) { }

  ngOnInit() {
    this.offersPage = 0;
    this.moreOffersButtonText = "Mostrar más";
    this.service.getOffers(this.offersPage).subscribe(
      offers => {
        this.offersPage++;
        this.offers = offers.content;
        this.offersPageActual = offers.totalPages;
      },
      error => {
        console.log(error);
      }
    )
  }

  moreEvents() {
    if (this.offersPage < this.offersPageActual) {
      this.service.getOffers(this.offersPage).subscribe(
        offers => {
          this.offersPage++;
          this.offers = this.offers.concat(offers.content);
        },
        error => {
          console.log(error);
        }
      )
    }
    else {
      this.moreOffersButtonText = 'No hay mas resultados';
    }
  }
}