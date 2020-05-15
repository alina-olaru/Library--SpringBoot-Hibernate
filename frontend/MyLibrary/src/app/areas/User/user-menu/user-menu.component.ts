import { LoginService } from 'src/app/areas/login/login.service';
import { Component, OnInit } from '@angular/core';
import {
faArrowRight
} from "@fortawesome/free-solid-svg-icons";
@Component({
  selector: 'app-user-menu',
  templateUrl: './user-menu.component.html',
  styleUrls: ['./user-menu.component.scss']
})
export class UserMenuComponent implements OnInit {

faArrowRight = faArrowRight;

  constructor(private auth :LoginService) { }

  ngOnInit(): void {
  }

  logOut(){
    this.auth.logOutUser();

  }
}
