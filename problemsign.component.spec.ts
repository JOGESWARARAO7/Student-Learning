import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProblemsignComponent } from './problemsign.component';

describe('ProblemsignComponent', () => {
  let component: ProblemsignComponent;
  let fixture: ComponentFixture<ProblemsignComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProblemsignComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProblemsignComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
