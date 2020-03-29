import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoadingService {

  private _loading: number = 0;
  loadingStatus: Subject<boolean>= new Subject();

  get loading():boolean {
    return this._loading == 0 ? false : true;
  }

  set loading(value) {
    this._loading = 1;
    this.loadingStatus.next(value);
  }

  start() {
    this._loading++;
    this.loadingStatus.next(true);
  }

  stop() {
    this._loading--;
    if(this._loading==0){
      this.loadingStatus.next(false);
    }
  }
}
