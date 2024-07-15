import { Component, OnInit } from '@angular/core';
import { CartService } from '../cart.service';
import { Part } from '../part';
import { PartComponent } from '../part/part.component';


@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  items = this.cartService.getItems();
  total1 = this.cartService.getTotal();

  constructor(
    private cartService: CartService
  ) { }

  onDeleteTask(index: number) {
    this.cartService.deleteItems(index);
  }

  addAnother(part: Part){
    this.cartService.incrementQuantity(part)
  }

  deleteAnother(part: Part){
    this.cartService.decrementQuantity(part)
  }


  clearCart(){
    this.cartService.clearCart();
  }

  endShopping(){
    this.cartService.checkOut()
  }

  getTotal(){
    return this.cartService.getTotal();
  }

  getTax(){
    return this.cartService.getTax();
  }
  getTotalTax(){
    return this.cartService.getTotalTax();
  }

  ngOnInit(): void {

  }

}
