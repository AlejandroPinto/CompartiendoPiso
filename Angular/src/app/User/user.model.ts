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
    admin?: boolean;
    roles?: string[];
    offers?: Offer[];
    reviews?: Review[];

}