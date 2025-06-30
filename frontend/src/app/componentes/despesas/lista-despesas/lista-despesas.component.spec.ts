import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaDespesasComponent } from './lista-despesas.component';

describe('ListaDespesasComponent', () => {
  let component: ListaDespesasComponent;
  let fixture: ComponentFixture<ListaDespesasComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListaDespesasComponent]
    });
    fixture = TestBed.createComponent(ListaDespesasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
