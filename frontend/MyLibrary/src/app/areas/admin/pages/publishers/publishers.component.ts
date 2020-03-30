
import { PublishersService } from './../publishers.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { TitleService } from './../../services/title.service';
import { DomSanitizer } from '@angular/platform-browser';
import { ToastrService } from 'src/app/services/toastr.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { LoadingService } from 'src/app/modules/loading-spinner/loading.service';
import { MatDialog } from '@angular/material/dialog';
import { ApiResponse } from 'src/app/Models/general/api-response';
import { ApiResponseType } from 'src/app/Models/general/api-response-type.enum';
import { Publisher } from 'src/app/Models/admin/PublisherModel';
import { AddEditPublisherComponent } from './add-edit-publisher/add-edit-publisher.component';
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
    public dialog : MatDialog,
    private publishersService : PublishersService
 ) { }

 ngOnInit() {
  this.GetPublishers();
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


GetPublishers() {
  this.loadingService.start();
  this.publishersService
    .GetPublishers()
    .subscribe((response: ApiResponse<Publisher[]>) => {
      this.loadingService.stop();

      if (response && response.status == ApiResponseType.SUCCESS) {
        if (response.body.length == 0) {
          this.toastr.Toast.fire({
            icon: 'info',
            title: 'Nu exista edituri in baza de date'
          });
        }

        this.publishers = response.body;
        this.updateDataSouce();
      }
    });
}

DeletePublisher(publisher:Publisher) {
  this.toastr.Swal.fire({
    title: 'Esti sigur ca vrei sa stergi aceasta editura?',
    html: `Id: <b>${publisher.publisherId}</b> - Nume: <b>${publisher.publisherTitle}</b>`,
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Da',
    cancelButtonText: 'Nu'
  }).then(result => {
    if (result.value) {
      this.loadingService.start();

      this.publishersService
        .DeletePublisher(publisher.publisherId)
        .subscribe((response: ApiResponse<boolean>) => {
          this.loadingService.stop();
          if (response && response.status==ApiResponseType.SUCCESS && response.body == true) {
            this.toastr.Toast.fire({
              title: 'Editura a fost stearsa.',
              icon: 'success'
            });
            this.GetPublishers();
          } else {
            this.toastr.Swal.fire(
              'Eroare!',
              'A aparut o eroare la stergere, incearca din nou!',
              'error'
            );
          }
        });
    }
  });
}

EditPublisher(item:Publisher) {
  const dialogRef=this.dialog.open(AddEditPublisherComponent,{
    width: '400px',
    data: {
      type: 'edit',
      model: Object.assign({}, item)
    }

  });

  dialogRef.afterClosed().subscribe(result => {
    if(result!=undefined && result !=null){
      this.EditPublisherConfirm(result,item);
    }
  })


}

EditPublisherConfirm(newPublisher:Publisher,oldPublisher:Publisher){
  this.loadingService.start();

  this.publishersService.UpdatePublisher(newPublisher,newPublisher.publisherId).subscribe((response:ApiResponse<Publisher>)=> {
    this.loadingService.stop();
    if (response && response.status == ApiResponseType.SUCCESS) {
      this.toastr.Toast.fire({
        title: 'Editura a fost editata cu succes!',
        icon: 'success'

      });

      const idxOld=this.publishers.indexOf(oldPublisher);
      this.publishers[idxOld]=newPublisher;
      this.updateDataSouce();
    }

    else {
      this.toastr.Swal.fire(
        'Eroare!',
        'A aparut o eroare la editare, incearca din nou!',
        'error'
      );


    }
  },
);
}

AddPublisher() {
  const dialogRef = this.dialog.open(AddEditPublisherComponent, {
    width: '400px',
    data: {
      type: 'add',
      model: this._addPublisher
    }
  });

  dialogRef.afterClosed().subscribe(result => {
    if (result != undefined && result != null) {
      this.AddPublisherConfirm(result);
    }
  });
}

AddPublisherConfirm(publisher: Publisher) {
  this.loadingService.start();
  this.publishersService
    .AddPublisher(publisher)
    .subscribe((response: ApiResponse<Publisher>) => {
      this.loadingService.stop();
      if (response && response.status == ApiResponseType.SUCCESS) {
        this.toastr.Toast.fire({
          title: 'Editura a fost adaugata cu succes',
          icon: 'success'

        });
        this.publishers.push(response.body);
        this.updateDataSouce();
      } else {
        this.toastr.Swal.fire(
          'Eroare!',
          'A aparut o eroare la adugare, incearca din nou!',
          'error'
        );


      }
    }, error => {
      this.toastr.Swal.fire(
        'Eroare!',
        'A aparut o eroare la adugare, incearca din nou!',
        'error'
      );
    });
}
}
