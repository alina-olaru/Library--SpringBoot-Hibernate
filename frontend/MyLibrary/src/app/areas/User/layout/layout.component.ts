import { Component, OnInit, Input } from '@angular/core';

import {
  faUser,
  faShoppingCart,
  faBookOpen,
  faGift,
  faCarSide,
  faBiking,
} from "@fortawesome/free-solid-svg-icons";



@Component({
  selector: 'app-user-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {


  faUser = faUser;
  faShoppingCart = faShoppingCart;
  faBookOpen = faBookOpen;
  faGiftfaGift = faGift;
  faCarSide = faCarSide;
  faBiking = faBiking;
  open:boolean = false;


  constructor() { }

  ngOnInit(): void {
  }


}


