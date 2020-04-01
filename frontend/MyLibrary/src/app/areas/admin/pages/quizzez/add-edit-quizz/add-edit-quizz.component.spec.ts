import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEditQuizzComponent } from './add-edit-quizz.component';

describe('AddEditQuizzComponent', () => {
  let component: AddEditQuizzComponent;
  let fixture: ComponentFixture<AddEditQuizzComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddEditQuizzComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddEditQuizzComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
