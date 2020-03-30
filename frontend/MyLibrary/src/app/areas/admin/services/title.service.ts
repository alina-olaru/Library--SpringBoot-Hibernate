import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { TitleServiceModel } from 'src/app/Models/admin/TitleServiceModel';

@Injectable({
  providedIn: 'root'
})
export class TitleService {

  private _titleSubject: Subject<TitleServiceModel> = new Subject();
  constructor() {
    this._titleSubject.next({
      icon: '',
      title: ''
    });
  }

  get titleSubject() {
    return this._titleSubject;
  }

  setTitle(icon: string, title: string) {
    this._titleSubject.next({ icon, title }as TitleServiceModel);
  }

  setTitleModel(model: TitleServiceModel) {
    this._titleSubject.next(model);
  }
}
