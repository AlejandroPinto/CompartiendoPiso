import { Offer } from '../offer/offer.model';
import { User } from '../user/user.model';

export interface Review {
	id?: number;
	valoration: number;
	comment: string;
    date?: string;
    offer?: Offer;
    user?: User; 
}