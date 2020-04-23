import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddressesBookComponent } from './addresses-book.component';

describe('AddressesBookComponent', () => {
  let component: AddressesBookComponent;
  let fixture: ComponentFixture<AddressesBookComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddressesBookComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddressesBookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
