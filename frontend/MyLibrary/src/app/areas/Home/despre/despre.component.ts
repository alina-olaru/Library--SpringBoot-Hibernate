import { Component, OnInit, HostListener, AfterViewInit } from "@angular/core";
import anime from "animejs/lib/anime.es.js";
import * as AOS from "aos";
@Component({
  selector: "app-despre",
  templateUrl: "./despre.component.html",
  styleUrls: ["./despre.component.scss"]
})
export class DespreComponent implements OnInit, AfterViewInit {
  aa: number = 1;

  constructor() {}
  ngAfterViewInit(): void {
    AOS.init({
      duration: 1200,
      delay: 200,
      once: true
    });
  }

  ngOnInit(): void {
  }
}
