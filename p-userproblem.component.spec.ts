import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PUserproblemComponent } from './p-userproblem.component';

describe('PUserproblemComponent', () => {
  let component: PUserproblemComponent;
  let fixture: ComponentFixture<PUserproblemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PUserproblemComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PUserproblemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
