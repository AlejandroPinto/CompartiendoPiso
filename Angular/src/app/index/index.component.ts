import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { IndexService } from '../index/index.service';
import { Index } from '../index/index.model';
import { Offer } from '../offer/offer.model';
import { User}  from '../user/user.model';

@Component({
  selector: 'index',
  templateUrl: './index.component.html'
})

export class IndexComponent {

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

  offers: Offer[] = [];

  queryBox: String;
  priceFrom: number;
  priceTo?: number;
  type?: String;
  rooms?: number;
  bathroom?: number;
  area?: number;
  page: number = 0

  formData: Index = {
    queryBox:"",
    priceFrom: 0,
    priceTo: 0,
    type: "empty",
    rooms: 0,
    bathroom: 0,
    area: 0,
    page: 0
  }

  constructor(private index: IndexService){}
  search(){
    if(this.queryBox !== undefined)
      this.formData.queryBox = this.queryBox;
    if(this.priceFrom !== undefined)
      this.formData.priceFrom = this.priceFrom;
    if(this.priceTo !== undefined)
      this.formData.priceTo = this.priceTo;
    if(this.type !== undefined)
      this.formData.type = this.type;
    if(this.rooms !== undefined)
      this.formData.rooms = this.rooms;
    if(this.bathroom !== undefined)
      this.formData.bathroom = this.bathroom;
    if(this.area !== undefined)
      this.formData.area = this.area;
    
    this.index.search(this.formData.queryBox,this.formData.priceFrom,this.formData.priceTo,this.formData.type,this.formData.rooms,this.formData.bathroom,this.formData.area,0).subscribe(
      offers => this.offers = offers,
    );
    this.page = 1;
  }

  showMore(){
    if(this.queryBox !== undefined)
      this.formData.queryBox = this.queryBox;
    if(this.priceFrom !== undefined)
      this.formData.priceFrom = this.priceFrom;
    if(this.priceTo !== undefined)
      this.formData.priceTo = this.priceTo;
    if(this.type !== undefined)
      this.formData.type = this.type;
    if(this.rooms !== undefined)
      this.formData.rooms = this.rooms;
    if(this.bathroom !== undefined)
      this.formData.bathroom = this.bathroom;
    if(this.area !== undefined)
      this.formData.area = this.area;
    
    this.index.search(this.formData.queryBox,this.formData.priceFrom,this.formData.priceTo,this.formData.type,this.formData.rooms,this.formData.bathroom,this.formData.area,this.page).subscribe(
      offers => this.offers = this.offers.concat(offers),
    );
    this.page += 1;
  }
}