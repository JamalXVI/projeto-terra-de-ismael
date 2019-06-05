import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TabelaReceitaComponent } from './tabela-receita.component';

describe('TabelaReceitaComponent', () => {
  let component: TabelaReceitaComponent;
  let fixture: ComponentFixture<TabelaReceitaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TabelaReceitaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TabelaReceitaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
