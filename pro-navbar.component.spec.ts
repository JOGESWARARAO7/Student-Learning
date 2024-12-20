import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProNavbarComponent } from './pro-navbar.component';

describe('ProNavbarComponent', () => {
  let component: ProNavbarComponent;
  let fixture: ComponentFixture<ProNavbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProNavbarComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProNavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
