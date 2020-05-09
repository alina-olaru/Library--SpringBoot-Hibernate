import { ThemeSelectorService } from "./modules/theme-selector/theme-selector.service";
import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"],
})
export class AppComponent implements OnInit {
  /**
   *
   */
  constructor(private themeSelectorServie: ThemeSelectorService) {}
  ngOnInit(): void {
    this.themeSelectorServie.init();
  }
}
