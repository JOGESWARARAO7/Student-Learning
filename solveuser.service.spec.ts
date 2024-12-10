import { TestBed } from '@angular/core/testing';

import { SolveuserService } from './solveuser.service';

describe('SolveuserService', () => {
  let service: SolveuserService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SolveuserService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
