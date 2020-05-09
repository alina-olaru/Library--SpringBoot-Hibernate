import { BehaviorSubject } from 'rxjs';
import { Injectable } from "@angular/core";
import { OverlayContainer } from "@angular/cdk/overlay";

@Injectable({
  providedIn: "root",
})
export class ThemeSelectorService {
  private _curentTheme: string = "mylibrary-light-1";
  curentTheme: BehaviorSubject<string>;
  constructor(private overlayContainer: OverlayContainer) {
    this.curentTheme = new BehaviorSubject<string>(this._curentTheme);
  }

  init() {
    this.checkLocalTheme();
  }

  checkLocalTheme() {
    let theme = JSON.parse(localStorage.getItem("mylibrary-theme"));

    if (theme != null) {
      this.overlayContainer
        .getContainerElement()
        .classList.remove(this._curentTheme);
      document.getElementById("body").classList.remove(this._curentTheme);

      this._curentTheme = theme;
      this.overlayContainer.getContainerElement().classList.add(theme);
      document.getElementById("body").classList.add(theme);
    }
    this.curentTheme.next(this._curentTheme);
  }

  changeTheme(theme: string) {
    this.overlayContainer
      .getContainerElement()
      .classList.remove(this._curentTheme);
    document.getElementById("body").classList.remove(this._curentTheme);

    this.overlayContainer.getContainerElement().classList.add(theme);
    document.getElementById("body").classList.add(theme);

    localStorage.setItem("mylibrary-theme", JSON.stringify(theme));

    this._curentTheme = theme;
    this.curentTheme.next(this._curentTheme);
  }
}
