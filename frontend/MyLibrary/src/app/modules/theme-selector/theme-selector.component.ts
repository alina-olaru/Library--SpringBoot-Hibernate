import { ThemeSelectorService } from "./theme-selector.service";
import { Component, OnInit } from "@angular/core";

import { faPaintBrush, faCircle } from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: "mylibrary-theme-selector",
  templateUrl: "./theme-selector.component.html",
  styleUrls: ["./theme-selector.component.scss"],
})
export class ThemeSelectorComponent implements OnInit {
  //------------themes--------------------
  curentTheme: string;
  faCircle = faCircle;
  faPaintBrush = faPaintBrush;
  constructor(private themeSelectorService: ThemeSelectorService) {
    this.themeSelectorService.curentTheme.subscribe((theme) => {
      this.curentTheme = theme;
    });
  }

  ngOnInit() {}

  changeTheme(theme: string) {
    this.themeSelectorService.changeTheme(theme);
  }
}
