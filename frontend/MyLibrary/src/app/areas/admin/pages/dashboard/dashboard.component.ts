import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'src/app/services/toastr.service';
import { TitleService } from '../../services/title.service';
import { LoadingService } from 'src/app/modules/loading-spinner/loading.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  constructor(
    private toastr: ToastrService,
    private titleService: TitleService,
    private loadingService: LoadingService
  ) {}

  ngOnInit(): void {
    this.titleService.setTitle('faTachometerAlt', 'Dashboard');
  }
}
