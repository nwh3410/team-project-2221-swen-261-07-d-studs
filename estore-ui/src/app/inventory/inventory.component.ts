import { Component, OnInit } from '@angular/core';
import { PartService } from '../part.service';
import { Part } from '../part';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css']
})
export class InventoryComponent implements OnInit {

  parts: Part[] = [];

  constructor(private partService: PartService
    ) { }

  ngOnInit(): void {
    this.getParts();
  }

  getParts(): void {
    this.partService.getParts()
        .subscribe(parts => this.parts = parts);
  }

  add(name: string,quantity:string, price: string, image:string): void {
    name = name.trim();
    const priceAsNumber = parseInt(price);
    const quantityAsNumber = parseInt(quantity);
    if (!name) { return; }
    this.partService.addPart({ name,quantity: quantityAsNumber, price: priceAsNumber, image } as Part)
      .subscribe(part => {
        this.parts.push(part);
      });
  }

  delete(part: Part): void {
    this.parts = this.parts.filter(p => p !== part);
    this.partService.deletePart(part.id).subscribe();
  }

  update(id: number, name: string,quantity:string, price: string, image:string): void {
    this.partService.getPart(id);
    name = name.trim();
    const priceAsNumber = parseInt(price);
    const quantityAsNumber = parseInt(quantity);
    this.partService.updatePart({id, name,quantity: quantityAsNumber, price: priceAsNumber, image } as Part)
      .subscribe();
      window.location.reload();
}

}
