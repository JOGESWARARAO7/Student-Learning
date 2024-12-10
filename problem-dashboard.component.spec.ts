import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProblemDashboardComponent } from './problem-dashboard.component';

describe('ProblemDashboardComponent', () => {
  let component: ProblemDashboardComponent;
  let fixture: ComponentFixture<ProblemDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProblemDashboardComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProblemDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
