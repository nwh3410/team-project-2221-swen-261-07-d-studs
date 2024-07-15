import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreBuiltsComponent } from './pre-builts.component';

describe('PreBuiltsComponent', () => {
  let component: PreBuiltsComponent;
  let fixture: ComponentFixture<PreBuiltsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PreBuiltsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PreBuiltsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
