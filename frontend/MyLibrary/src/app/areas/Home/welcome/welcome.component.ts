import { Component, OnInit } from "@angular/core";
import { SliderComponent } from "../slider/slider.component";
import {
  faUser,
  faShoppingCart,
  faBookOpen,
  faGift,
  faCarSide,
  faBiking,
} from "@fortawesome/free-solid-svg-icons";
@Component({
  selector: "app-welcome",
  templateUrl: "./welcome.component.html",
  styleUrls: ["./welcome.component.scss"],
})
export class WelcomeComponent implements OnInit {
  faUser = faUser;
  faShoppingCart = faShoppingCart;
  faBookOpen = faBookOpen;
  faGiftfaGift = faGift;
  faCarSide = faCarSide;
  faBiking = faBiking;
  open:boolean = false;

  constructor() {}

  ngOnInit(): void {}


}

/*

*/
