import { Component, OnInit, ViewChild } from '@angular/core';
import { NgImageSliderModule, NgImageSliderComponent } from 'ng-image-slider';

@Component({
  selector: 'app-slider',
  templateUrl: './slider.component.html',
  styleUrls: ['./slider.component.scss']
})
export class SliderComponent implements OnInit {

  @ViewChild('nav') slider: NgImageSliderComponent;
  size: any = {width: '100%', height: '50%', space: 0};
  imageObject: Array<object> = [
    {

    image: '../../../../assets/images/details/1160_make-notes-d.jpg', // Support base64 image
    thumbImage:   '../../../../assets/images/details/1160_make-notes-d.jpg', // Support base64 image
    alt: '-25% la toate cartile editurii Leda',
    title: '-25% la toate cartile editurii Leda'
},
{

  image:   '../../../../assets/images/details/1162_knock-knock-d.jpg', // Support base64 image
  thumbImage:  '../../../../assets/images/details/1162_knock-knock-d.jpg', // Support base64 image
  alt: '-15% la toate cartile editurii Rao',
  title: '-15% la toate cartile editurii Rao'
},
{

  image:  '../../../../assets/images/details/3866_trebo-d.jpg', // Support base64 image
  thumbImage: '../../../../assets/images/details/3866_trebo-d.jpg', // Support base64 image
  alt: '-25% la toate cartile editurii Girasol',
  title: '-25% la toate cartile editurii Girasol'
},
{

  image:  '../../../../assets/images/details/486_lichidari-stoc-d.jpg', // Support base64 image
  thumbImage:  '../../../../assets/images/details/486_lichidari-stoc-d.jpg', // Support base64 image
  alt: '-20% la toate cartile editurii Aramis',
  title: '-20% la toate cartile editurii Aramis'
},
{

  image:  '../../../../assets/images/details/5086_nuuna-d.jpg', // Support base64 image
  thumbImage: '../../../../assets/images/details/5086_nuuna-d.jpg', // Support base64 image
  alt: '-20% la toate cartile editurii DPH',
  title: '-20% la toate cartile editurii DPH'
},
{

  image: '../../../../assets/images/details/7055_mk-d.jpg', // Support base64 image
  thumbImage: '../../../../assets/images/details/7055_mk-d.jpg', // Support base64 image
  alt: '-25% la toate cartile editurii Epica',
  title: '-25% la toate cartile editurii Epica'
},
{

  image: '../../../../assets/images/details/7159_raft-film-gorzo-d.jpg', // Support base64 image
  thumbImage: '../../../../assets/images/details/7159_raft-film-gorzo-d.jpg', // Support base64 image
  alt: '-25% la toate cartile editurii Humanitas Junior',
  title: '-25% la toate cartile editurii  Humanitas Junior'
},

{

  image: '../../../../assets/images/details/7510_d_raft.jpg', // Support base64 image
  thumbImage: '../../../../assets/images/details/7510_d_raft.jpg', // Support base64 image
  alt: '-25% la toate cartile editurii Humanitas Junior',
  title: '-25% la toate cartile editurii  Humanitas Junior'
},
{

  image: '../../../../assets/images/details/7652_dsupravituire.jpg', // Support base64 image
  thumbImage: '../../../../assets/images/details/7652_dsupravituire.jpg', // Support base64 image
  alt: '-25% la toate cartile editurii Humanitas Junior',
  title: '-25% la toate cartile editurii  Humanitas Junior'
},
{

  image: '../../../../assets/images/details/7716_antidotlaplictiseala-d.jpg', // Support base64 image
  thumbImage: '../../../../assets/images/details/7716_antidotlaplictiseala-d.jpg', // Support base64 image
  alt: '-25% la toate cartile editurii Humanitas Junior',
  title: '-25% la toate cartile editurii  Humanitas Junior'
},
{

  image: '../../../../assets/images/details/8185_d-jurnale.jpg', // Support base64 image
  thumbImage:  '../../../../assets/images/details/8185_d-jurnale.jpg', // Support base64 image
  alt: '-25% la toate cartile editurii Humanitas Junior',
  title: '-25% la toate cartile editurii  Humanitas Junior'
},
{

  image: '../../../../assets/images/details/8271_carte-d.jpg', // Support base64 image
  thumbImage:  '../../../../assets/images/details/8271_carte-d.jpg', // Support base64 image
  alt: '-25% la toate cartile editurii Humanitas Junior',
  title: '-25% la toate cartile editurii  Humanitas Junior'
},
// {

//   image: '../../../../assets/images/details/bgpopup.png', // Support base64 image
//   thumbImage: '../../../../assets/images/details/bgpopup.png', // Support base64 image
//   alt: '-25% la toate cartile editurii Humanitas Junior',
//   title: '-25% la toate cartile editurii  Humanitas Junior'
// },
// {

//   image: '../../../../assets/images/details/headerModalPcte.png', // Support base64 image
//   thumbImage: '../../../../assets/images/details/headerModalPcte.png', // Support base64 image
//   alt: '-25% la toate cartile editurii Humanitas Junior',
//   title: '-25% la toate cartile editurii  Humanitas Junior'
// },
// {

//   image: '../../../../assets/images/details/podcast-cartu.jpg', // Support base64 image
//   thumbImage: '../../../../assets/images/details/podcast-cartu.jpg', // Support base64 image
//   alt: '-25% la toate cartile editurii Humanitas Junior',
//   title: '-25% la toate cartile editurii  Humanitas Junior'
// },
{

  image: '../../../../assets/images/details/seducatorul_domn_comanda.jpg', // Support base64 image
  thumbImage:'../../../../assets/images/details/seducatorul_domn_comanda.jpg', // Support base64 image
  alt: '-25% la toate cartile editurii Humanitas Junior',
  title: '-25% la toate cartile editurii  Humanitas Junior'
},

// {

//   image: '../../../../assets/images/details/sprite_index.png', // Support base64 image
//   thumbImage:'../../../../assets/images/details/sprite_index.png', // Support base64 image
//   alt: '-25% la toate cartile editurii Humanitas Junior',
//   title: '-25% la toate cartile editurii  Humanitas Junior'
// },
{

  image: '../../../../assets/images/details/work-from-home.jpg', // Support base64 image
  thumbImage: '../../../../assets/images/details/work-from-home.jpg', // Support base64 image
  // alt: '-25% la toate cartile editurii Humanitas Junior',
  // title: '-25% la toate cartile editurii  Humanitas Junior'
},
{

  image: '../../../../assets/images/details/new/8311_Pikto web.jpg', // Support base64 image
  thumbImage: '../../../../assets/images/details/new/8311_Pikto web.jpg', // Support base64 image
  // alt: '-25% la toate cartile editurii Humanitas Junior',
  // title: '-25% la toate cartile editurii  Humanitas Junior'
},
{

  image: '../../../../assets/images/details/new/8383_d-1170x400-1.jpg', // Support base64 image
  thumbImage:  '../../../../assets/images/details/new/8383_d-1170x400-1.jpg', // Support base64 image
  // alt: '-25% la toate cartile editurii Humanitas Junior',
  // title: '-25% la toate cartile editurii  Humanitas Junior'
},
{

  image: '../../../../assets/images/details/new/8391_d (7).jpg', // Support base64 image
  thumbImage:  '../../../../assets/images/details/new/8391_d (7).jpg', // Support base64 image
  // alt: '-25% la toate cartile editurii Humanitas Junior',
  // title: '-25% la toate cartile editurii  Humanitas Junior'
},
{

  image: '../../../../assets/images/details/new/8467_dtarg.jpg', // Support base64 image
  thumbImage:  '../../../../assets/images/details/new/8467_dtarg.jpg', // Support base64 image
  // alt: '-25% la toate cartile editurii Humanitas Junior',
  // title: '-25% la toate cartile editurii  Humanitas Junior'
},
{

  image: '../../../../assets/images/details/new/8475_dnemira.jpg', // Support base64 image
  thumbImage: '../../../../assets/images/details/new/8475_dnemira.jpg', // Support base64 image
  // alt: '-25% la toate cartile editurii Humanitas Junior',
  // title: '-25% la toate cartile editurii  Humanitas Junior'
}






];


  constructor() { }

  ngOnInit(): void {
  }


}
