import { DashboardService } from "./dashboard.service";
import { Component, OnInit, NgZone } from "@angular/core";
import { ToastrService } from "src/app/services/toastr.service";
import { TitleService } from "../../services/title.service";
import { LoadingService } from "src/app/modules/loading-spinner/loading.service";
import * as am4core from "@amcharts/amcharts4/core";
import * as am4charts from "@amcharts/amcharts4/charts";
import am4themes_animated from "@amcharts/amcharts4/themes/animated";
import { Category } from "src/app/Models/admin/CategoryModel";
import { Router } from "@angular/router";
import { LandingBooksService } from "src/app/areas/Home/welcome/LandingBooks.service";
import { ApiResponseType } from "src/app/Models/general/api-response-type.enum";
import { ApiResponse } from "src/app/Models/general/api-response";

am4core.useTheme(am4themes_animated);

@Component({
  selector: "app-dashboard",
  templateUrl: "./dashboard.component.html",
  styleUrls: ["./dashboard.component.scss"],
})
export class DashboardComponent implements OnInit {
  private chart: am4charts.XYChart;
  private chart_second: am4charts.XYChart;
  private chart_third: am4charts.XYChart;
  private chart_fourth: am4charts.XYChart;
  private chart_fifth: am4charts.XYChart;
  Categories: Category[];
  CategoriesTitles: string[] = [];
  countOfBooks: number;

  //-----------------sch1----------------------------
  countBookCategories: number[] = [];
  titlesCategories: string[] = [];
  CategoriesWithNumber: any[] = [];
  AuthorsWithBooks : any[]=[];

  constructor(
    private toastr: ToastrService,
    private titleService: TitleService,
    private loadingService: LoadingService,
    public landingBookService: LandingBooksService,
    public router: Router,
    private zone: NgZone,
    private dash: DashboardService
  ) {}

  ngOnInit() {

  }

  //------------------------------ADUCEM INFORMATIILE-----------------------------------

  //-----------------------------CHART 1------------------------------------------------

  getCategoriesWithNumberBooks() {
    return this.dash
      .getCategoriesWithNumberBooks()
      .subscribe((response: ApiResponse<any[]>) => {
        if (response && response.status == ApiResponseType.SUCCESS) {
          this.CategoriesWithNumber = response.body;
          this.createChartCategories();
        } else {
          if (response.body.length == 0) {
            this.toastr.Toast.fire({
              icon: "info",
              title: "Nu exista edituri in baza de date",
            });
          }
        }
      });

    console.log(this.CategoriesWithNumber + " dasshhh");
  }

  ngAfterViewInit() {
    this.getCategoriesWithNumberBooks();
    this.getAuthorsWithBooks();
    this.getBooksAuthorsCategory();
    //NR DE CARTI -> CATEGORIE (TOT VANDUTE)
    //AUTOR + CARTI VANDUTE
    //AUTOR + NR CARTI EXISTENTE PE SITE
    //CIFRA AFACERI PE LUNA
    //la third:autor + carti







  }

  createChartCategories(){
    this.zone.runOutsideAngular(() => {
      let chart = am4core.create("chartdiv", am4charts.XYChart);

      chart.paddingRight = 20;


      chart.data = this.CategoriesWithNumber;

      console.log(this.CategoriesWithNumber.length + "dataaaaaaaaaaaaaaaa");

      let categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
      categoryAxis.dataFields.category = "titleOfCategory";
      categoryAxis.renderer.labels.template.rotation = 270;
      categoryAxis.renderer.labels.template.hideOversized = false;
      categoryAxis.renderer.minGridDistance = 20;
      categoryAxis.renderer.labels.template.horizontalCenter = "right";
      categoryAxis.renderer.labels.template.verticalCenter = "middle";
      categoryAxis.tooltip.label.rotation = 270;
      categoryAxis.tooltip.label.horizontalCenter = "right";
      categoryAxis.tooltip.label.verticalCenter = "middle";

      let valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
      valueAxis.title.text = "Categorii";
      valueAxis.title.fontWeight = "bold";

      // Create series
      let series = chart.series.push(new am4charts.ColumnSeries3D());
      series.dataFields.valueY = "numberBooksforCategory";
      series.dataFields.categoryX = "titleOfCategory";
      series.name = "Raport";
      series.tooltipText = "{categoryX}: [bold]{valueY}[/]";
      series.columns.template.fillOpacity = 0.8;

      let columnTemplate = series.columns.template;
      columnTemplate.strokeWidth = 2;
      columnTemplate.strokeOpacity = 1;
      columnTemplate.stroke = am4core.color("#FFFFFF");

      columnTemplate.adapter.add("fill", function (fill, target) {
        return chart.colors.getIndex(target.dataItem.index);
      });

      columnTemplate.adapter.add("stroke", function (stroke, target) {
        return chart.colors.getIndex(target.dataItem.index);
      });

      chart.cursor = new am4charts.XYCursor();
      chart.cursor.lineX.strokeOpacity = 0;
      chart.cursor.lineY.strokeOpacity = 0;
    });
  }


  //-----------------------------CHART 2------------------------------------------------



  getAuthorsWithBooks(){

    return this.dash
    .getAuthorsNumberBooks()
    .subscribe((response: ApiResponse<any[]>) => {
      if (response && response.status == ApiResponseType.SUCCESS) {
        this.AuthorsWithBooks = response.body;
        this.createChartAuthorsWithBooks();
      } else {
        if (response.body.length == 0) {
          this.toastr.Toast.fire({
            icon: "info",
            title: "Nu exista autori in baza de date",
          });
        }
      }
    });

  console.log(this.CategoriesWithNumber + " dasshhh");

  }
  createChartAuthorsWithBooks(){
    this.zone.runOutsideAngular(() => {
      let chart_second = am4core.create(
        "chartdiv_second",
        am4charts.PieChart3D
      );
      chart_second.hiddenState.properties.opacity = 0; // this creates initial fade-in

      chart_second.data = this.AuthorsWithBooks;

      chart_second.innerRadius = am4core.percent(40);
      chart_second.depth = 120;

      chart_second.legend = new am4charts.Legend();

      var series = chart_second.series.push(new am4charts.PieSeries3D());
      series.dataFields.value = "numberBooksforCategory";
      series.dataFields.depthValue = "numberBooksforCategory";
      series.dataFields.category = "titleOfCategory";
      series.slices.template.cornerRadius = 5;
      series.colors.step = 3;
    });
  }


  //-----------------------------CHART 3------------------------------------------------

  getBooksAuthorsCategory(){

    this.createchartBooksAuthorsCategory();
  }
  createchartBooksAuthorsCategory(){
    let chart = am4core.create("chartdiv_third", am4charts.PieChart);

// Let's cut a hole in our Pie chart the size of 40% the radius
chart.innerRadius = am4core.percent(40);

// Add data
chart.data = [{
  "country": "Lithuania",
  "litres": 501.9,
  "bottles": 1500
}, {
  "country": "Czech Republic",
  "litres": 301.9,
  "bottles": 990
}, {
  "country": "Ireland",
  "litres": 201.1,
  "bottles": 785
}, {
  "country": "Germany",
  "litres": 165.8,
  "bottles": 255
}, {
  "country": "Australia",
  "litres": 139.9,
  "bottles": 452
}, {
  "country": "Austria",
  "litres": 128.3,
  "bottles": 332
}, {
  "country": "UK",
  "litres": 99,
  "bottles": 150
}, {
  "country": "Belgium",
  "litres": 60,
  "bottles": 178
}, {
  "country": "The Netherlands",
  "litres": 50,
  "bottles": 50
}];

// Add and configure Series
let pieSeries = chart.series.push(new am4charts.PieSeries());
pieSeries.dataFields.value = "litres";
pieSeries.dataFields.category = "country";
pieSeries.slices.template.stroke = am4core.color("#fff");
pieSeries.slices.template.strokeWidth = 2;
pieSeries.slices.template.strokeOpacity = 1;

// Disabling labels and ticks on inner circle
pieSeries.labels.template.disabled = true;
pieSeries.ticks.template.disabled = true;

// Disable sliding out of slices
pieSeries.slices.template.states.getKey("hover").properties.shiftRadius = 0;
pieSeries.slices.template.states.getKey("hover").properties.scale = 0.9;

// Add second series
let pieSeries2 = chart.series.push(new am4charts.PieSeries());
pieSeries2.dataFields.value = "bottles";
pieSeries2.dataFields.category = "country";
pieSeries2.slices.template.stroke = am4core.color("#fff");
pieSeries2.slices.template.strokeWidth = 2;
pieSeries2.slices.template.strokeOpacity = 1;
pieSeries2.slices.template.states.getKey("hover").properties.shiftRadius = 0;
pieSeries2.slices.template.states.getKey("hover").properties.scale = 1.1;
  }

  ngOnDestroy() {
    this.zone.runOutsideAngular(() => {
      if (this.chart) {
        this.chart.dispose();
      }
      if (this.chart_second) {
        this.chart_second.dispose();
      }

      if (this.chart_third) {
        this.chart_third.dispose();
      }
      if (this.chart_fourth) {
        this.chart_fourth.dispose();
      }
    });
  }
}
