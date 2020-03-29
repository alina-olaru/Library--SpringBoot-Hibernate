import { Component, OnInit, OnDestroy } from '@angular/core';
import { LoadingService } from './loading.service';
import { Observable, Subscription } from 'rxjs';

@Component({
  selector: 'app-loading-spinner',
  templateUrl: './loading-spinner.component.html',
  styleUrls: ['./loading-spinner.component.scss']
})
export class LoadingSpinnerComponent implements OnInit,OnDestroy {

  loading: boolean = false;
  loaderSubscriber: Subscription;
  constructor(private loadingService: LoadingService) { }

  ngOnDestroy(): void {
    this.loaderSubscriber.unsubscribe();
  }

  ngOnInit() {
    this.loaderSubscriber = this.loadingService.loadingStatus.subscribe(e => {
      this.loading = e;
    });
  }

}
