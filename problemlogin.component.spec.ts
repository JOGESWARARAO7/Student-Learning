import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProblemloginComponent } from './problemlogin.component';

describe('ProblemloginComponent', () => {
  let component: ProblemloginComponent;
  let fixture: ComponentFixture<ProblemloginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProblemloginComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProblemloginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
