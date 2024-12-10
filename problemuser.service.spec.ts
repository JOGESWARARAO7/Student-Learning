import { TestBed } from '@angular/core/testing';

import { ProblemuserService } from './problemuser.service';

describe('ProblemuserService', () => {
  let service: ProblemuserService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProblemuserService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
