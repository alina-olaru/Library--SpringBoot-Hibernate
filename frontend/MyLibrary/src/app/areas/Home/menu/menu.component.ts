import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }




   toggmenu()
   {
     console.log("click");
    $(this).toggleClass('nb-open');
    $('.site-nav').toggleClass('sn-open');
    $('.bar').toggleClass('b-open');
  }
}



