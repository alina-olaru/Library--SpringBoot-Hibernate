import { MatTableDataSource } from '@angular/material/table';
import { TitleService } from './../../services/title.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ToastrService } from 'src/app/services/toastr.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Category } from 'src/app/Models/admin/CategoryModel';
import { LoadingService } from 'src/app/modules/loading-spinner/loading.service';
import { MatDialog } from '@angular/material/dialog';

import { ApiResponse } from 'src/app/Models/general/api-response';
import { ApiResponseType } from 'src/app/Models/general/api-response-type.enum';
import { CategoryService } from './Category.service';
import { AddEditCategoryComponent } from './add-edit-category/add-edit-category.component';




@Component({
  selector: 'app-Category',
  templateUrl: './Category.component.html',
  styleUrls: ['./Category.component.scss']



})
export class CategoryComponent implements OnInit {


  @ViewChild(MatSort, { static: false }) sort: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;

  Categories: Category[] = [];
  _addCategory: Category;
  displayedColumns: string[] = ['categoryId', 'categoryTitle', 'categoryDescription', 'actions'];
  dataSource: MatTableDataSource<Category> = new MatTableDataSource(this.Categories);

  constructor(
    private titleService: TitleService,
    private toastr: ToastrService,
    private sanitizer: DomSanitizer,
    private loadingService: LoadingService,
    public dialog: MatDialog,
    public CategoriesService: CategoryService
  ) {}

  ngOnInit() {
    this.GetCategories();
    this.titleService.setTitle('faGlobeEurope', 'Categorii');
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  updateDataSouce() {
    this.dataSource = new MatTableDataSource(this.Categories);
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }
  GetCategories() {
    this.loadingService.start();
    this.CategoriesService
      .GetCategory()
      .subscribe((response: ApiResponse<Category[]>) => {
        this.loadingService.stop();

        if (response && response.status == ApiResponseType.SUCCESS) {
          if (response.body.length == 0) {
            this.toastr.Toast.fire({
              icon: 'info',
              title: 'Nu exista category in baza de date'
            });
          }

          this.Categories = response.body;
          this.updateDataSouce();
        }
      });
  }

  DeleteCategory(category:Category) {
    this.toastr.Swal.fire({
      title: 'Esti sigur ca vrei sa stergi acest bautura?',
      html: `Id: <b>${category.categoryId}</b> - Nume: <b>${category.categoryTitle}</b>`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Da',
      cancelButtonText: 'Nu'
    }).then(result => {
      if (result.value) {
        this.loadingService.start();

        this.CategoriesService
          .DeleteCategory(category.categoryId)
          .subscribe((response: ApiResponse<boolean>) => {
            this.loadingService.stop();
            if (response && response.status==ApiResponseType.SUCCESS && response.body == true) {
              this.toastr.Toast.fire({
                title: 'categoria a fost stearsa!.',
                icon: 'success'
              });
              this.GetCategories();
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

  EditCategory(item:Category) {
    const dialogRef=this.dialog.open(AddEditCategoryComponent,{
      width: '400px',
      data: {
        type: 'edit',
        model: Object.assign({}, item)
      }

    });

    dialogRef.afterClosed().subscribe(result => {
      if(result!=undefined && result !=null){
        this.EditCategoryConfirm(result,item);
      }
    })


  }

  EditCategoryConfirm(newCategory:Category,oldCategory:Category){
    this.loadingService.start();

    this.CategoriesService.UpdateCategory(newCategory,newCategory.categoryId).subscribe((response:ApiResponse<Category>)=> {
      this.loadingService.stop();
      if (response && response.status == ApiResponseType.SUCCESS) {
        this.toastr.Toast.fire({
          title: 'Categoria a fost editata cu succes!',
          icon: 'success'

        });

        const idxOld=this.Categories.indexOf(oldCategory);
        this.Categories[idxOld]=newCategory;
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

  AddCategory() {
    const dialogRef = this.dialog.open(AddEditCategoryComponent, {
      width: '400px',
      data: {
        type: 'add',
        model: this._addCategory
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result != undefined && result != null) {
        this.AddCategoryConfirm(result);
      }
    });
  }

  AddCategoryConfirm(Category: Category) {
    this.loadingService.start();
    this.CategoriesService
      .AddCategory(Category)
      .subscribe((response: ApiResponse<Category>) => {
        this.loadingService.stop();
        if (response && response.status == ApiResponseType.SUCCESS) {
          this.toastr.Toast.fire({
            title: 'categoria a fost adaugata cu succes',
            icon: 'success'

          });
          this.Categories.push(response.body);
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
