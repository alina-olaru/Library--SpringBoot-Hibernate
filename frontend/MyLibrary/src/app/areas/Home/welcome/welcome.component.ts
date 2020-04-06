import { Component, OnInit } from '@angular/core';
import { SliderComponent} from '../slider/slider.component';
import {
faUser,
faShoppingCart,
faBookOpen
} from "@fortawesome/free-solid-svg-icons";@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.scss']
})
export class WelcomeComponent implements OnInit {

  faUser=faUser;
  faShoppingCart=faShoppingCart;
  faBookOpen=faBookOpen;
  constructor() { }

  ngOnInit(): void {
  }


}

/*

*/
