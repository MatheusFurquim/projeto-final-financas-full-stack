import { TestBed } from '@angular/core/testing';

import { PerfilRiscoService } from './perfil-risco.service';

describe('PerfilRiscoService', () => {
  let service: PerfilRiscoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PerfilRiscoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
