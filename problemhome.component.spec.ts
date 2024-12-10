import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProblemhomeComponent } from './problemhome.component';

describe('ProblemhomeComponent', () => {
  let component: ProblemhomeComponent;
  let fixture: ComponentFixture<ProblemhomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProblemhomeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProblemhomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
