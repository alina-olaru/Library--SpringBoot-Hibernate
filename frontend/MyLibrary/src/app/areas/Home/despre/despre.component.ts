import { Component, OnInit } from '@angular/core';
import {
  faUser,
  faShoppingCart,
  faBookOpen,
  faGift,
  faCarSide,
  faBiking,
} from "@fortawesome/free-solid-svg-icons";
@Component({
  selector: 'app-despre',
  templateUrl: './despre.component.html',
  styleUrls: ['./despre.component.scss']
})
export class DespreComponent implements OnInit {
open:boolean =false;
faUser = faUser;
faShoppingCart = faShoppingCart;
faBookOpen = faBookOpen;
faGiftfaGift = faGift;
faCarSide = faCarSide;
faBiking = faBiking;
  constructor() { }

  ngOnInit(): void {
  }


  test(){

    if(this.open==false){
      this.open=true;
    }
    else{
      this.open=false;
    }

  }

}
