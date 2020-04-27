import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddBookViaOCRComponent } from './add-book-via-ocr.component';

describe('AddBookViaOCRComponent', () => {
  let component: AddBookViaOCRComponent;
  let fixture: ComponentFixture<AddBookViaOCRComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddBookViaOCRComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddBookViaOCRComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
