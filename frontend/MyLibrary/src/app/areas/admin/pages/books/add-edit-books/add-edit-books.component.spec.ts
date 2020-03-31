import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEditBooksComponent } from './add-edit-books.component';

describe('AddEditBooksComponent', () => {
  let component: AddEditBooksComponent;
  let fixture: ComponentFixture<AddEditBooksComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddEditBooksComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddEditBooksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
