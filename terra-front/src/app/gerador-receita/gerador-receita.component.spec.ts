import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GeradorReceitaComponent } from './gerador-receita.component';

describe('GeradorReceitaComponent', () => {
  let component: GeradorReceitaComponent;
  let fixture: ComponentFixture<GeradorReceitaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GeradorReceitaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GeradorReceitaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
