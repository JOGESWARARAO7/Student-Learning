import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProblemotpComponent } from './problemotp.component';

describe('ProblemotpComponent', () => {
  let component: ProblemotpComponent;
  let fixture: ComponentFixture<ProblemotpComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProblemotpComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProblemotpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
