import { Component, OnInit } from '@angular/core';
import { Part } from '../part';
import { PartService } from '../part.service';

@Component({
  selector: 'app-part',
  templateUrl: './part.component.html',
  styleUrls: ['./part.component.css']
})
export class PartComponent implements OnInit {

  parts: Part[] = [];

  constructor(private partService: PartService) { }

  ngOnInit(): void {
    this.getParts();
  }

  getParts(): void {
    this.partService.getParts()
        .subscribe(parts => this.parts = parts);
  }

}
