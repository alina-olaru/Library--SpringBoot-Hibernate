import { Component, OnInit } from '@angular/core';
import anime from 'animejs/lib/anime.es.js';

@Component({
  selector: 'app-despre',
  templateUrl: './despre.component.html',
  styleUrls: ['./despre.component.scss']
})
export class DespreComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    // anime({
    //   targets: 'div',
    //   translateX: 250,
    //   rotate: '1turn',
    //   backgroundColor: '#FFF',
    //   duration: 800
    // });
  }

}
