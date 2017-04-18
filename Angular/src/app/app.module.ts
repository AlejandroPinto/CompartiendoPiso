import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { routing } from './app.routing';

import { AppComponent } from './app.component';
import { FooterComponent } from './footer.component';
import { HeaderComponent } from './header.component';
import { ContactComponent } from './contact/contact.component';
import { RegisterComponent } from './register/register.component';
import { AdModifyComponent } from './adModify/adModify.component';
import { AdminComponent } from './admin/admin.component';
import { IndexComponent } from './index/index.component';


@NgModule({
  declarations: [
    AppComponent,ContactComponent,RegisterComponent,AdModifyComponent,AdminComponent,FooterComponent,HeaderComponent, IndexComponent
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
