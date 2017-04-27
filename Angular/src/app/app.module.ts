import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule,JsonpModule,Http } from '@angular/http';
import { routing } from './app.routing';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';

import { SigninComponent } from './signin/signin.component';
import { RegisterComponent } from './register/register.component';
import { SigninService } from './signin/signin.service';
import { HttpClient } from "./HttpClient/httpClient";

import { IndexComponent } from './index/index.component';
import { IndexService } from './index/index.service';
import { ContactComponent } from './contact/contact.component';

import { NewOfferComponent } from './offer/newOffer.component';
import { OfferComponent } from './offer/offer.component';
import { AdModifyComponent } from './offer/adModify.component';
import { OfferService } from './offer/offer.service';

import { UserComponent } from './user/user.component';
import { EditUserComponent } from './user/edit_user.component';
import { AdminComponent } from './admin/admin.component';
import { UserService } from './user/user.service';
import { RegisterService } from './register/register.service';


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
    routing,JsonpModule
  ],
  bootstrap: [AppComponent],
  providers: [OfferService, UserService, SigninService, HttpClient,IndexService,RegisterService]
})
export class AppModule { }
