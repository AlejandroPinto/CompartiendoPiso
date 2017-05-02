import { Offer } from '../offer/offer.model';

export interface Characteristic {
	id?: number;
	name: string;
	value: boolean;
    offer?: Offer;
}