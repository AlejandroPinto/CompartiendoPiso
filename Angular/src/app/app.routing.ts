import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { IndexComponent } from './index/index.component';
import { ContactComponent } from './contact/contact.component';
import { AdminComponent } from './admin/admin.component';
import { SigninComponent } from './signin/signin.component';
import { NewOfferComponent } from './offer/newOffer.component';
import { OfferComponent } from './offer/offer.component';
import { AdModifyComponent } from './offer/adModify.component';
import { UserComponent } from './user/user.component';
import { EditUserComponent } from './user/edit_user.component';


const appRoutes = [
    { path: 'register', component: RegisterComponent },
    { path: 'index', component: IndexComponent },
    { path: 'signin', component: SigninComponent },
    { path: 'contact', component: ContactComponent },
    { path: 'admin', component: AdminComponent },
    { path: 'offer/:id', component: OfferComponent },
    { path: 'newOffer', component: NewOfferComponent },
    { path: 'adModify', component: AdModifyComponent },
    { path: 'user/:id', component: UserComponent },
    { path: 'editUser', component: EditUserComponent },
    { path: '', redirectTo: 'index', pathMatch: 'full' }
]
export const routing = RouterModule.forRoot(appRoutes);