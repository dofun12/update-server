import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListVersaoComponent } from './list-versao.component';

describe('ListVersaoComponent', () => {
  let component: ListVersaoComponent;
  let fixture: ComponentFixture<ListVersaoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListVersaoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListVersaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
