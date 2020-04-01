import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEditVouchersComponent } from './add-edit-vouchers.component';

describe('AddEditVouchersComponent', () => {
  let component: AddEditVouchersComponent;
  let fixture: ComponentFixture<AddEditVouchersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddEditVouchersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddEditVouchersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
