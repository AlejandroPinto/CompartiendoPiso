import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/Rx';

import { Offer } from './offer.model';
import { Review } from '../review/review.model';
import { HttpClient } from '../HttpClient/httpClient';


const BASE_URL = 'https://localhost:8443/api/offer/';
const BASE_URL_OFFER = 'https://localhost:8443/api/offer/';
const BASE_URL_REVIEW = 'https://localhost:8443/api/review/';

@Injectable()
export class OfferService {

    constructor(private http: HttpClient) { }

	getOffers(page: number) {
		return this.http.get(BASE_URL + "?page=" + page +"&size=10")
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	getAllOffers() {
        return this.http.get(BASE_URL_OFFER).map(
            response => response.json())
            .catch(error => this.handleError(error))
    }

	getOffer(id: number | string) {
		return this.http.get(BASE_URL + id)
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	createOffer(offer: Offer) {
		return this.http.post(BASE_URL, offer)
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	deleteOffer(id: number | string) {
		return this.http.delete(BASE_URL_OFFER + id)
			.map(response => response.json())
			.catch(error => this.handleError(error));
	}

	updateOffer(id: number | string, offer: Offer){
		return this.http.put(BASE_URL + id,offer)
            .map(response => {response.json(),console.log(response)})
            .catch(error => this.handleError(error));
	}

	private handleError(error: any) {
		console.error(error);
		return Observable.throw("Server error (" + error.status + "): " + error.text())
	}

	addReviews(id: number | string, review: Review){
		return this.http.post(BASE_URL_REVIEW + id, review)
			.map(response => response.json())
			.catch(error => this.handleError(error));

	}
}