import { TestBed } from '@angular/core/testing';

import { VersaoServiceService } from './versao-service.service';

describe('VersaoServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: VersaoServiceService = TestBed.get(VersaoServiceService);
    expect(service).toBeTruthy();
  });
});
