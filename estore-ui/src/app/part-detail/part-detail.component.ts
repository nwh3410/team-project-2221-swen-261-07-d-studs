import { Component, OnInit, Input } from '@angular/core';

import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { Part } from '../part';
import { PartService } from '../part.service';
import { CartService } from '../cart.service';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-part-detail',
  templateUrl: './part-detail.component.html',
  styleUrls: ['./part-detail.component.css']
})
export class PartDetailComponent implements OnInit {

  @Input() part?: Part;
  

  constructor(
    private route: ActivatedRoute,
    private partService: PartService,
    private location: Location,
    private cartService: CartService,
    private appComponent: AppComponent
  ) {}

  /*
  called in the HTML file and used to add parts from the inventory to the personal shopping cart
  after the user adds a part to their cart, computer alerts the user the part was added for conformation
  */
  addToCart(part: Part) {
    this.cartService.addToCart(part);
    window.alert('Your product has been added to the cart!');
  }

  /*
  when the program starts the inventory shows all the parts on the screen
  */
  ngOnInit(): void {
    this.getPart();
  }

  /*
  goes throught the parts list and gets all parts in the inventory
  */
  getPart(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.partService.getPart(id).subscribe(part => this.part = part);
  }

  /*
  this button returns the user to the parts list after the user clicks on a specific part to see more information about the part
  */
  goBack(): void {
    this.location.back();
  }

  isAdmin = this.appComponent.getAdminStatus

}
