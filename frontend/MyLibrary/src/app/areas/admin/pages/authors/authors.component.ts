import { MatTableDataSource } from '@angular/material/table';
import { TitleService } from './../../services/title.service';
import { Component, OnInit, ViewChild } from '@angular/core';import { DomSanitizer } from '@angular/platform-browser';
import { ToastrService } from 'src/app/services/toastr.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Author } from 'src/app/Models/admin/AuthorModel';
import { LoadingService } from 'src/app/modules/loading-spinner/loading.service';
import { MatDialog } from '@angular/material/dialog';
import {AuthorsService } from './authors.service';
import { ApiResponse } from 'src/app/Models/general/api-response';
import { ApiResponseType } from 'src/app/Models/general/api-response-type.enum';

@Component({
  selector: 'app-authors',
  templateUrl: './authors.component.html',
  styleUrls: ['./authors.component.scss']
})
export class AuthorsComponent implements OnInit {


  @ViewChild(MatSort, { static: false }) sort: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;

  authors:Author[]=[];
  displayedColumns:string[]=[
    'authorId',
    'firstName',
    'lastName',
    'actions'
  ];
  dataSource:MatTableDataSource<Author>=
  new MatTableDataSource(this.authors);


  constructor(private titleService:TitleService,
    private toastr: ToastrService,
    private sanitizer: DomSanitizer,
    private loadingService: LoadingService,
    public dialog: MatDialog,
    public authorsService:AuthorsService

    ) { }



ngOnInit() {

  this.GetAuthors();
  this.titleService.setTitle('faGlobeEurope', 'Autori');
}

applyFilter(filterValue:string){
  this.dataSource.filter=filterValue.trim().toLowerCase();
}

updateDataSouce(){
  this.dataSource=new MatTableDataSource(this.authors);
  this.dataSource.sort=this.sort;
  this.dataSource.paginator=this.paginator;
}
GetAuthors(){

  this.loadingService.start();
  this.authorsService.GetAuthors().subscribe((response:ApiResponse<Author[]>) =>{

    this.loadingService.stop();

    if(response && response.status == ApiResponseType.SUCCESS){

    if(response.body.length==0){
      this.toastr.Toast.fire({
        icon:"info",
        title:"Nu exista autori in baza de date"
      });
    }

    this.authors=response.body;
    this.updateDataSouce();
  }
  })


}

DeleteAuthor(element){


}

EditAuthor(element){


}

AddAuthor(){

}
}
