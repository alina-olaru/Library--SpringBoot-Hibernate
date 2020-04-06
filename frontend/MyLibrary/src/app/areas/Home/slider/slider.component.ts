import { Component, OnInit } from '@angular/core';
import { NgImageSliderModule } from 'ng-image-slider';

@Component({
  selector: 'app-slider',
  templateUrl: './slider.component.html',
  styleUrls: ['./slider.component.scss']
})
export class SliderComponent implements OnInit {


  size: any = {width: '100%', height: '50%', space: 0};
  imageObject: Array<object> = [
    {

    image: '../../../../assets/images/landingpage/slider_1.png', // Support base64 image
    thumbImage: '../../../../assets/images/landingpage/slider_1.png', // Support base64 image
    alt: '-25% la toate cartile editurii Leda',
    title: '-25% la toate cartile editurii Leda'
},
{

  image: '../../../../assets/images/landingpage/slider_2.jpg', // Support base64 image
  thumbImage: '../../../../assets/images/landingpage/slider_2.jpg', // Support base64 image
  alt: '-15% la toate cartile editurii Rao',
  title: '-15% la toate cartile editurii Rao'
},
{

  image: '../../../../assets/images/landingpage/slider_3.jpg', // Support base64 image
  thumbImage: '../../../../assets/images/landingpage/slider_3.jpg', // Support base64 image
  alt: '-25% la toate cartile editurii Girasol',
  title: '-25% la toate cartile editurii Girasol'
},
{

  image: '../../../../assets/images/landingpage/slider_4.jpg', // Support base64 image
  thumbImage: '../../../../assets/images/landingpage/slider_4.jpg', // Support base64 image
  alt: '-20% la toate cartile editurii Aramis',
  title: '-20% la toate cartile editurii Aramis'
},
{

  image: '../../../../assets/images/landingpage/slider_5.jpg', // Support base64 image
  thumbImage: '../../../../assets/images/landingpage/slider_5.jpg', // Support base64 image
  alt: '-20% la toate cartile editurii DPH',
  title: '-20% la toate cartile editurii DPH'
},
{

  image: '../../../../assets/images/landingpage/slider_6.jpg', // Support base64 image
  thumbImage: '../../../../assets/images/landingpage/slider_6.jpg', // Support base64 image
  alt: '-25% la toate cartile editurii Epica',
  title: '-25% la toate cartile editurii Epica'
},
{

  image: '../../../../assets/images/landingpage/slider_8.jpg', // Support base64 image
  thumbImage: '../../../../assets/images/landingpage/slider_8.jpg', // Support base64 image
  alt: '-25% la toate cartile editurii Humanitas Junior',
  title: '-25% la toate cartile editurii  Humanitas Junior'
},





];


  constructor() { }

  ngOnInit(): void {
  }

}
