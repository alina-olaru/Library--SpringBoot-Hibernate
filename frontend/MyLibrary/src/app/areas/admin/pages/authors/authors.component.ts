import { TitleService } from './../../services/title.service';
import { Component, OnInit, ViewChild } from '@angular/core';import { DomSanitizer } from '@angular/platform-browser';
import { ToastrService } from 'src/app/services/toastr.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';


@Component({
  selector: 'app-authors',
  templateUrl: './authors.component.html',
  styleUrls: ['./authors.component.scss']
})
export class AuthorsComponent implements OnInit {


  @ViewChild(MatSort, { static: false }) sort: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;

  displayedColumns:string[]=[
    'ID_AUTOR',
    'NUME',
    'PRENUME',
    'ACTIONS'
  ];



  constructor(private titleService:TitleService,
    private toastr: ToastrService,
    private sanitizer: DomSanitizer,

    ) { }



ngOnInit() {

  this.titleService.setTitle('faGlobeEurope', 'Autori');
}

DeleteAuthor(element){


}

EditAuthor(element){


}

AddAuthor(){

}
}
