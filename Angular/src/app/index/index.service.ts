import { Injectable } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { OfferService } from '../offer/offer.service';
import 'rxjs/Rx';

import { HttpClient } from '../HttpClient/httpClient';
import {Offer} from '../offer/offer.model';


const BASE_URL = 'https://localhost:8443/api/';

@Injectable()
export class IndexService{

	offer: Offer;
	authCreds: string;
	page: number;
    constructor(private http: HttpClient, private router: Router, private offerService: OfferService){}
	ngOnInit() {
		this.page = 0;
  	}

	search(queryBox: String, priceFrom: number, priceTo: number, type: String, rooms: number, bathroom: number,area: number,page: number){
		return this.http.get(BASE_URL+"search?&queryBox="+queryBox+"&priceFrom="+priceFrom+"&priceTo="+priceTo+"&type="+type+"&rooms="+rooms+"&bathroom="+bathroom+"&area="+area+"&attributes=&page="+page).map(
			response => response.json(),
		)._catch(
			error => this.handleError(error)
		)
	}

	

	// getPhotoUser(userName :string){
	// 	return this.http.get(BASE_URL+userName+'/photo')
	// 	.map(response => response.json())
	// 	.catch(error => this.handleError(error));
	// }

	//updated user avatar

    private handleError(error: any) {
		console.error(error);
		return Observable.throw("Server error (" + error.status + "): " + error.text())
	}
}