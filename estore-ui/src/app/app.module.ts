import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { PartComponent } from './part/part.component';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { MessagesComponent } from './messages/messages.component';
import { PartSearchComponent } from './part-search/part-search.component';
import { AppRoutingModule } from './app-routing.module';
import { PartDetailComponent } from './part-detail/part-detail.component';
import { LoginComponent } from './login/login.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { InventoryComponent } from './inventory/inventory.component';
import { PreBuiltsComponent } from './pre-builts/pre-builts.component';



@NgModule({
  declarations: [
    AppComponent,
    PartComponent,
    MessagesComponent,
    PartSearchComponent,
    PartDetailComponent,
    LoginComponent,
    ShoppingCartComponent,
    InventoryComponent,
    PreBuiltsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
