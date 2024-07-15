import { Component, OnInit } from '@angular/core';
import { Part } from '../part';
import { PartService } from '../part.service';

@Component({
  selector: 'app-pre-builts',
  templateUrl: './pre-builts.component.html',
  styleUrls: ['./pre-builts.component.css']
})
export class PreBuiltsComponent implements OnInit {

  parts: Part[] = [];

  constructor(private partService: PartService) { }

  ngOnInit(): void {
    this.getParts();
  }

  getParts(): void {
    this.partService.getPart(700).subscribe(parts => this.parts[0] = parts);
    this.partService.getPart(701).subscribe(parts => this.parts[1] = parts);
    this.partService.getPart(702).subscribe(parts => this.parts[2] = parts);
    this.partService.getPart(703).subscribe(parts => this.parts[3] = parts);
    this.partService.getPart(704).subscribe(parts => this.parts[4] = parts);
    this.partService.getPart(705).subscribe(parts => this.parts[5] = parts);
    this.partService.getPart(706).subscribe(parts => this.parts[6] = parts);
    this.partService.getPart(801).subscribe(parts => this.parts[7] = parts);
    this.partService.getPart(802).subscribe(parts => this.parts[8] = parts);
    this.partService.getPart(803).subscribe(parts => this.parts[9] = parts);
    this.partService.getPart(804).subscribe(parts => this.parts[10] = parts);
    this.partService.getPart(805).subscribe(parts => this.parts[11] = parts);
    this.partService.getPart(806).subscribe(parts => this.parts[12] = parts);
    this.partService.getPart(807).subscribe(parts => this.parts[13] = parts);
    this.partService.getPart(808).subscribe(parts => this.parts[14] = parts);
    this.partService.getPart(809).subscribe(parts => this.parts[15] = parts)
    
  }

}
