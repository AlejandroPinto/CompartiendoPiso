import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { routing } from './app.routing';

import { AppComponent } from './app.component';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { ContactComponent } from './contact/contact.component';
import { RegisterComponent } from './register/register.component';
import { AdModifyComponent } from './offer/adModify.component';
import { AdminComponent } from './admin/admin.component';
import { IndexComponent } from './index/index.component';
import { NavbarComponent } from './navbar/navbar.component';
import { SigninComponent } from './signin/signin.component';
import { NewOfferComponent } from './offer/newOffer.component';
import { OfferComponent } from './offer/offer.component';
import { UserComponent } from './user/user.component';
import { EditUserComponent } from './user/edit_user.component';


@NgModule({
  declarations: [
    AppComponent,ContactComponent,RegisterComponent,SigninComponent,
    AdModifyComponent,AdminComponent,FooterComponent,HeaderComponent, IndexComponent, NavbarComponent,
    NewOfferComponent,OfferComponent,EditUserComponent,UserComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
