import { User } from '../user/user.model';
import { Characteristic } from '../offer/characteristics.model';
import { Review } from '../review/review.model';

export interface Offer {
	id?: number;
	title: string;
	price: number;
    description?: string;
    province: string;
    location?: string;
    neighborhood?: string;
    area?: number;
    bathroom?: number;
    room?: number;
    type : string;
    user: User;
    reviewList: Review[];
    characteristics : Characteristic[];
}