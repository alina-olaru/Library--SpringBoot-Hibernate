import { Component, OnInit, AfterViewInit } from '@angular/core';
import Parallax from 'parallax-js';
import * as $ from 'jquery';

@Component({
  selector: 'app-error404',
  templateUrl: './error404.component.html',
  styleUrls: ['./error404.component.scss']
})
export class Error404Component implements OnInit, AfterViewInit {
  constructor() {}
  ngAfterViewInit(): void {
    $('.not-found').on('mousemove', this.parallax);
    $('.not-found').on('mouseleave', this.stopParallax);
  }

  ngOnInit(): void {

  }

    parallax (e: JQueryEventObject)
     {

        var windowWidth = $(window).width();

        if (windowWidth < 768) {return};
        var halfFieldWidth = $('.parallax').width() / 2;
        var halfFieldHeight = $('.parallax').height() / 2;
        var fieldPos = $('.parallax').offset();
        var  x = e.pageX;
        var y = e.pageY - fieldPos.top;
        var  newX = (x - halfFieldWidth) / 30;
        var   newY = (y - halfFieldHeight) / 30;
        $('.parallax [class*="wave"]').each((index, e) => {
          $(e).css({
            transition: '',
            transform:
              'translate3d(' + index * newX + 'px,' + index * newY + 'px,0px)'
          });
        });
      };

    stopParallax ()
     {
        $('.parallax [class*="wave"]').css({
          transform: 'translate(0px,0px)',
          transition: 'all .2s'
        });
        setTimeout(() => {
          $('.parallax [class*="wave"]').css('transition', '');
        }, 700);
      };




  }

