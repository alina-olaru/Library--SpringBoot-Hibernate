import { TitleService } from './../../services/title.service';
import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ToastrService } from 'src/app/services/toastr.service';


@Component({
  selector: 'app-authors',
  templateUrl: './authors.component.html',
  styleUrls: ['./authors.component.scss']
})
export class AuthorsComponent implements OnInit {

  constructor(private titleService:TitleService,
    private toastr: ToastrService,
    private sanitizer: DomSanitizer,

    ) { }



ngOnInit() {

  this.titleService.setTitle('faGlobeEurope', 'Autori');
}
}
