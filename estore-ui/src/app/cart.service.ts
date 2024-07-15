import { Part } from './part';
import { Injectable } from '@angular/core';
import { Location } from '@angular/common';
import { partition } from 'rxjs';
import { PartComponent } from './part/part.component';
import { ElementSchemaRegistry } from '@angular/compiler';

@Injectable({
  providedIn: 'root'
})

export class CartService {
  //used to hold parts the user puts in shopping cart
  items: Part[] = []
  //used in the addToCart method
  counter = 0
  //used to keep track of the users total in the cart
  total = 0
  //used to calulate tax
  tax = 0
  //used to calculate total with tax
  totalTax = 0


  /*
  this method is responsible for adding items into the cart when the user clicks the buy button on the part detail page
  if there is no items in the cart the method will just add the item with a quantity of one
  if there are items in the cart, it will loop through the list and look for the part with the same id
  if the part is found it will add one to the quanity
  if the part is not found the method will add the part to the shopping cart with an element of one
  */
   addToCart(part: Part) {
    const variable = this.counter
    if (this.counter == 0){
      part.quantity = 1
      this.total += part.price
      this.items.push(part)
      this.counter++
    }else{
        this.items.forEach(element => {
        if (element.id == part.id){
          element.quantity += 1
          this.total += element.price
          this.counter++
        }
    });
  } 
    if (variable == this.counter){
      part.quantity = 1
      this.total += part.price
      this.items.push(part)
      console.log(this.items)
    }
}

/*
used in the shopping cart page where the user can add more of the same items to their cart
*/

  incrementQuantity(part: Part){
    this.items.forEach(element => {
        if (part.id == element.id)
          element.quantity = element.quantity + 1
          this.total += element.price
    });

  }
  

  /*
  used in the shopping cart page where the user can delete one an item from their cart
  */
  decrementQuantity(part: Part){
    this.items.forEach(element => {
      if (part.id == element.id)
        element.quantity = element.quantity - 1
        this.total -= element.price
  });
  }
  

  /*
  used to return all items the user added to their shopping cart.
  */
  getItems() {
    return this.items;
  }

  /*
  deletes the items the users gets rid of in the cart no matter how many items are in the cart.
  */
  deleteItems(index: number){
    this.items.forEach(element => {
      if (element.id == this.items[index].id){
        this.total -= element.quantity*element.price
        this.items.splice(index, 1)
      }
    });
    return this.items
  }

  /*
  this method is called when the user presses a button that allows them to checkout
  the method first checks to see if there is anyhting in the cart
  if there is nothing in the cart then the program will say there is nothing in the cart to checkout
  if there is stuff in the cart the program diplays the total and a message thanking the user for shopping at the store
  */
  checkOut(){
    if (this.total <= 0){
      window.alert("There are no items in the cart")
    }else if (this.total > 0){
      window.alert("Thanks for shopping at STUDLY COMPUTER PARTS Your Total is:" + this.totalTax)
    this.clearCart()
    }
    this.total = 0
  }

  clearCart() {
    this.items.splice(0);
    this.total = 0
  }

  getTotal(){
    return this.total
  }

  getTax(){
    this.tax = this.total * 0.08
    return this.tax
  }
  getTotalTax(){
    this.totalTax = this.total + this.tax
    return this.totalTax
  }

  constructor(
    private location: Location,
  ) { }
}