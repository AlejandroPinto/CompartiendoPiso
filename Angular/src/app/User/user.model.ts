import { Offer } from '../offer/offer.model';
import { Review } from '../review/review.model';

export interface User {
	id?: number;
	name: string;
	firstLastName: string;
    secondLastName: string;
    email: string;
    phone: number;
    pass: string;
    description?: string;
    photo?: string;
    admin?: boolean;
    roles?: string[];
    offerList?: Offer[];
    reviewList: Review[];

}