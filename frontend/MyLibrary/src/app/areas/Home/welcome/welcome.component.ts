import { Component, OnInit } from '@angular/core';
import { TimelineLite, Back, Power1 } from 'gsap'


@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.scss']
})
export class WelcomeComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {

  }


  try(){
    gsap.to(window, {duration: 2, scrollTo: {y: 400, x: 250}});

  }
}

/*

*/
