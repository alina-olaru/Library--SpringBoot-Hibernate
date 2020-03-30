import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { TitleService } from './../../services/title.service';
import { DomSanitizer } from '@angular/platform-browser';
import { ToastrService } from 'src/app/services/toastr.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Author } from 'src/app/Models/admin/AuthorModel';
import { LoadingService } from 'src/app/modules/loading-spinner/loading.service';
import { MatDialog } from '@angular/material/dialog';
import { ApiResponse } from 'src/app/Models/general/api-response';
import { ApiResponseType } from 'src/app/Models/general/api-response-type.enum';
import { Publisher } from 'src/app/Models/admin/PublisherModel';
@Component({
  selector: 'app-publishers',
  templateUrl: './publishers.component.html',
  styleUrls: ['./publishers.component.scss']
})
export class PublishersComponent implements OnInit {

  @ViewChild(MatSort, { static: false }) sort: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;

 publishers:Publisher[] = [];
 _addPublisher:Publisher;
  displayedColumns: string[] = ['publisherId', 'publisherTitle', 'actions'];
  dataSource: MatTableDataSource<Publisher> = new MatTableDataSource(this.publishers);



  constructor(    private titleService: TitleService,
    private toastr: ToastrService,
    private sanitizer: DomSanitizer,
    private loadingService: LoadingService,
    public dialog: MatDialog
 ) { }

 ngOnInit() {
  this.GetPubishers();
  this.titleService.setTitle('faGlobeEurope', 'Edituri');
}

applyFilter(filterValue: string) {
  this.dataSource.filter = filterValue.trim().toLowerCase();
}

updateDataSouce() {
  this.dataSource = new MatTableDataSource(this.publishers);
  this.dataSource.sort = this.sort;
  this.dataSource.paginator = this.paginator;
}

GetPubishers(){


}
}
