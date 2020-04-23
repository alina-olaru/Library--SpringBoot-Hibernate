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

  constructor() { }

  ngOnInit(): void {
  }

}
