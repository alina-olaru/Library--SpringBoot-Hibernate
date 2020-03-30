import { BookUser } from './../../../Models/BookUser';
import { Component, OnInit } from '@angular/core';

import {

  faUserCircle,
  faTachometerAlt,
  IconDefinition
} from "@fortawesome/free-solid-svg-icons";
import { TitleServiceModel } from 'src/app/Models/admin/TitleServiceModel';
import { TitleService } from '../services/title.service';
import { LoginService } from '../../login/login.service';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {

  faTachometerAlt = faTachometerAlt;
  faUserCircle = faUserCircle;
  title: TitleServiceModel;
  openSidebar = true;
  icon: IconDefinition;
  iconString: string;
  loaded: boolean = false;

  user: BookUser = null;

  constructor(
    private titleService: TitleService,
    private loginService: LoginService
  ) {}

  ngOnInit() {
    this.titleService.titleSubject.subscribe(e => {
      setTimeout(() => {
        this.title = e;
        this.SetIcon();
      }, 5);
    });

    this.user = this.loginService.getUser();
  }

  SetIcon() {
    switch (this.title.icon) {
      case "faTachometerAlt":
        this.icon = faTachometerAlt;
        break;
      default:
        this.icon = faTachometerAlt;
    }

    this.loaded = true;
  }

  LogOut() {
    this.loginService.logOutUser();
  }

  ngOnDestroy(): void {
    this.titleService.titleSubject.unsubscribe();
  }

}
