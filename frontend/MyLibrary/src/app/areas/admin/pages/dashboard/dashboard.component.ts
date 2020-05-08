import { DashboardService } from './dashboard.service';
import { Component, OnInit, NgZone } from '@angular/core';
import { ToastrService } from 'src/app/services/toastr.service';
import { TitleService } from '../../services/title.service';
import { LoadingService } from 'src/app/modules/loading-spinner/loading.service';
import * as am4core from "@amcharts/amcharts4/core";
import * as am4charts from "@amcharts/amcharts4/charts";
import am4themes_animated from "@amcharts/amcharts4/themes/animated";
import { Category } from 'src/app/Models/admin/CategoryModel';
import { Router } from '@angular/router';
import { LandingBooksService } from 'src/app/areas/Home/welcome/LandingBooks.service';
import { ApiResponseType } from 'src/app/Models/general/api-response-type.enum';
import { ApiResponse } from 'src/app/Models/general/api-response';
import { CategoryNumberBooks } from 'src/app/Models/dashboard/CategoryNumberBooks';

am4core.useTheme(am4themes_animated);

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  private chart:am4charts.XYChart;
  private chart_second:am4charts.XYChart;
  private chart_third:am4charts.XYChart;
  private chart_fourth:am4charts.XYChart;
  private chart_fifth:am4charts.XYChart;
  Categories: Category[];
  CategoriesTitles: string[] = [];
  countOfBooks: number;
  //-----------------sch1----------------------------
  countBookCategories:number[]=[];
  titlesCategories:string[]=[];
  CategoriesWithNumber:CategoryNumberBooks[]=[];

  constructor(
    private toastr: ToastrService,
    private titleService: TitleService,
    private loadingService: LoadingService,
    public landingBookService: LandingBooksService,
    public router: Router,
    private zone:NgZone,
    private dash:DashboardService
  ) {

      }

      ngOnInit(){
        this.getCategoriesWithNumberBooks();

      }




      //------------------------------ADUCEM INFORMATIILE-----------------------------------

            //-----------------------------CHART 1---------------------------------

      getCategoriesWithNumberBooks(){

        return this.dash.getCategoriesWithNumberBooks().subscribe((response:ApiResponse<CategoryNumberBooks[]>) =>{

      if (response && response.status == ApiResponseType.SUCCESS) {
        console.log("s-a intrat in req");
        if (response.body.length == 0) {
          this.toastr.Toast.fire({
            icon: "info",
            title: "Nu exista edituri in baza de date"
          });
        }
      }
      else{

        this.CategoriesWithNumber=response.body;
      }
        })

        console.log(this.CategoriesWithNumber+ " dasshhh");
      }


    ngAfterViewInit() {
      //NR DE CARTI -> CATEGORIE (TOT VANDUTE)
      //AUTOR + CARTI VANDUTE
      //AUTOR + NR CARTI EXISTENTE PE SITE
      //CIFRA AFACERI PE LUNA
      //la third:autor + carti
      this.zone.runOutsideAngular(() => {
        let chart = am4core.create("chartdiv", am4charts.XYChart);



        chart.paddingRight = 20;

        let data = [];
        let visits = 10;
        for (let i = 1; i < 366; i++) {
          visits += Math.round((Math.random() < 0.5 ? 1 : -1) * Math.random() * 10);
          data.push({ date: new Date(2018, 0, i), name: "name" + i, value: visits });
        }

        chart.data = this.CategoriesWithNumber;

        console.log(this.CategoriesWithNumber.length + "dataaaaaaaaaaaaaaaa");


        let categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
categoryAxis.dataFields.category = "country";
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
series.columns.template.fillOpacity = .8;

let columnTemplate = series.columns.template;
columnTemplate.strokeWidth = 2;
columnTemplate.strokeOpacity = 1;
columnTemplate.stroke = am4core.color("#FFFFFF");

columnTemplate.adapter.add("fill", function(fill, target) {
  return chart.colors.getIndex(target.dataItem.index);
})

columnTemplate.adapter.add("stroke", function(stroke, target) {
  return chart.colors.getIndex(target.dataItem.index);
})

chart.cursor = new am4charts.XYCursor();
chart.cursor.lineX.strokeOpacity = 0;
chart.cursor.lineY.strokeOpacity = 0;



      });

      this.zone.runOutsideAngular(() => {
        let chart_second = am4core.create("chartdiv_second", am4charts.PieChart3D);
        chart_second.hiddenState.properties.opacity = 0; // this creates initial fade-in

        chart_second.data = [
  {
    country: "Lithuania",
    litres: 501.9
  },
  {
    country: "Czech Republic",
    litres: 301.9
  },
  {
    country: "Ireland",
    litres: 201.1
  },
  {
    country: "Germany",
    litres: 165.8
  },
  {
    country: "Australia",
    litres: 139.9
  },
  {
    country: "Austria",
    litres: 128.3
  }
];

chart_second.innerRadius = am4core.percent(40);
chart_second.depth = 120;

chart_second.legend = new am4charts.Legend();

var series = chart_second.series.push(new am4charts.PieSeries3D());
series.dataFields.value = "litres";
series.dataFields.depthValue = "litres";
series.dataFields.category = "country";
series.slices.template.cornerRadius = 5;
series.colors.step = 3;
      });

      this.zone.runOutsideAngular(() => {


        let chart_third = am4core.create("chartdiv_third", am4charts.ChordDiagram);

        // colors of main characters
        chart_third.colors.saturation = 0.45;
        chart_third.colors.step = 3;
        let colors = {
            Rachel: chart_third.colors.next(),
            Monica: chart_third.colors.next(),
            Phoebe: chart_third.colors.next(),
            Ross: chart_third.colors.next(),
            Joey: chart_third.colors.next(),
            Chandler: chart_third.colors.next()
        }

        // data was provided by: https://www.reddit.com/user/notrudedude

        chart_third.data = [
        // node property fields take data from data items where they are first mentioned, that's
        // why we add empty data items at the beginning and set colors here
        {"from":"Monica", "image":"monica.png", "color":colors.Monica},
        {"from":"Rachel", "image":"rachel.png", "color":colors.Rachel},
        {"from":"Chandler", "image":"chandler.png", "color":colors.Chandler},
        {"from":"Ross", "image":"ross.png", "color":colors.Ross},
        {"from":"Joey", "color":colors.Joey, "image":"joey.png",},
        {"from":"Phoebe", "image":"phoebe.png", "color":colors.Phoebe},

        // real data
        {"from":"Monica","to":"Rachel","value":4},
        {"from":"Monica","to":"Chandler","value":113},
        {"from":"Monica","to":"Ross","value":16},
        {"from":"Monica","to":"Joey","value":9},
        {"from":"Monica","to":"Phoebe","value":3},
        {"from":"Monica","to":"Paul the wine guy","value":1},
        {"from":"Monica","to":"Mr Geller","value":6},
        {"from":"Monica","to":"Mrs Geller","value":5},
        {"from":"Monica","to":"Aunt Lilian","value":1},
        {"from":"Monica","to":"Nana","value":1},
        {"from":"Monica","to":"Young Ethan","value":5},
        {"from":"Monica","to":"Ben","value":3},
        {"from":"Monica","to":"Fun Bobby","value":3},
        {"from":"Monica","to":"Richard","value":16},
        {"from":"Monica","to":"Mrs Green","value":1},
        {"from":"Monica","to":"Paolo2","value":1},
        {"from":"Monica","to":"Pete","value":10},
        {"from":"Monica","to":"Chip","value":1},
        {"from":"Monica","to":"Timothy (Burke)","value":1},
        {"from":"Monica","to":"Emily","value":1},
        {"from":"Monica","to":"Dr. Roger","value":3},
        {"from":"Rachel","to":"Chandler","value":7},
        {"from":"Rachel","to":"Ross","value":80},
        {"from":"Rachel","to":"Joey","value":30},
        {"from":"Rachel","to":"Phoebe","value":6},
        {"from":"Rachel","to":"Paolo","value":5},
        {"from":"Rachel","to":"Mr Geller","value":2},
        {"from":"Rachel","to":"Mrs Geller","value":1},
        {"from":"Rachel","to":"Barry","value":1},
        {"from":"Rachel","to":"Dr Green","value":3},
        {"from":"Rachel","to":"Mark3","value":1},
        {"from":"Rachel","to":"Josh","value":2},
        {"from":"Rachel","to":"Gunther","value":1},
        {"from":"Rachel","to":"Joshua","value":3},
        {"from":"Rachel","to":"Danny","value":1},
        {"from":"Rachel","to":"Mr. Zelner","value":1},
        {"from":"Rachel","to":"Paul Stevens","value":3},
        {"from":"Rachel","to":"Tag","value":4},
        {"from":"Rachel","to":"Melissa","value":1},
        {"from":"Rachel","to":"Gavin","value":2},
        {"from":"Chandler","to":"Joey","value":1},
        {"from":"Chandler","to":"Phoebe","value":7},
        {"from":"Chandler","to":"Aurora","value":2},
        {"from":"Chandler","to":"Jill Goodacre","value":1},
        {"from":"Chandler","to":"Janice","value":11},
        {"from":"Chandler","to":"Mrs Bing","value":3},
        {"from":"Chandler","to":"Nina","value":1},
        {"from":"Chandler","to":"Susie","value":5},
        {"from":"Chandler","to":"Mary Theresa","value":1},
        {"from":"Chandler","to":"Ginger","value":2},
        {"from":"Chandler","to":"Joanna","value":5},
        {"from":"Chandler","to":"Kathy","value":7},
        {"from":"Chandler","to":"Mr Bing","value":1},
        {"from":"Ross","to":"Joey","value":3},
        {"from":"Ross","to":"Phoebe","value":18},
        {"from":"Ross","to":"Carol","value":10},
        {"from":"Ross","to":"Mrs Geller","value":8},
        {"from":"Ross","to":"Aunt Lilian","value":1},
        {"from":"Ross","to":"Mrs Bing","value":3},
        {"from":"Ross","to":"Celia","value":2},
        {"from":"Ross","to":"Julie","value":6},
        {"from":"Ross","to":"Ben","value":6},
        {"from":"Ross","to":"Mrs Green","value":2},
        {"from":"Ross","to":"Chloe","value":1},
        {"from":"Ross","to":"Bonnie","value":4},
        {"from":"Ross","to":"Messy Girl (Cheryl)","value":5},
        {"from":"Ross","to":"Emily","value":12},
        {"from":"Ross","to":"Jill","value":1},
        {"from":"Ross","to":"Elizabeth","value":8},
        {"from":"Ross","to":"Aunt Millie","value":2},
        {"from":"Ross","to":"Mona","value":11},
        {"from":"Ross","to":"Emma","value":7},
        {"from":"Ross","to":"Charlie","value":10},
        {"from":"Joey","to":"Phoebe","value":6},
        {"from":"Joey","to":"Janice","value":1},
        {"from":"Joey","to":"Lorraine","value":2},
        {"from":"Joey","to":"Melanie","value":2},
        {"from":"Joey","to":"Erica","value":2},
        {"from":"Joey","to":"Mrs Green","value":1},
        {"from":"Joey","to":"Kate","value":4},
        {"from":"Joey","to":"Lauren","value":2},
        {"from":"Joey","to":"Estelle","value":1},
        {"from":"Joey","to":"Kathy","value":2},
        {"from":"Joey","to":"Emily","value":4},
        {"from":"Joey","to":"Katie","value":2},
        {"from":"Joey","to":"Janine","value":9},
        {"from":"Joey","to":"Erin","value":1},
        {"from":"Joey","to":"Cecilia","value":3},
        {"from":"Joey","to":"Charlie","value":3},
        {"from":"Phoebe","to":"David","value":14},
        {"from":"Phoebe","to":"Roger","value":1},
        {"from":"Phoebe","to":"Duncan","value":1},
        {"from":"Phoebe","to":"Rob Dohnen","value":2},
        {"from":"Phoebe","to":"Ryan","value":5},
        {"from":"Phoebe","to":"Malcom","value":1},
        {"from":"Phoebe","to":"Robert","value":1},
        {"from":"Phoebe","to":"Sergei","value":1},
        {"from":"Phoebe","to":"Vince","value":2},
        {"from":"Phoebe","to":"Jason","value":1},
        {"from":"Phoebe","to":"Rick","value":2},
        {"from":"Phoebe","to":"Gunther","value":1},
        {"from":"Phoebe","to":"Gary","value":7},
        {"from":"Phoebe","to":"Jake","value":2},
        {"from":"Phoebe","to":"Eric","value":3},
        {"from":"Phoebe","to":"Mike","value":18},
        {"from":"Carol","to":"Ben","value":1},
        {"from":"Carol","to":"Susan","value":1},
        {"from":"Mr Geller","to":"Mrs Geller","value":3},
        {"from":"Frank","to":"Alice","value":5}]



        chart_third.dataFields.fromName = "from";
        chart_third.dataFields.toName = "to";
        chart_third.dataFields.value = "value";


        chart_third.nodePadding = 0.5;
        chart_third.minNodeSize = 0.01;
        chart_third.startAngle = 80;
        chart_third.endAngle = chart_third.startAngle + 360;
        chart_third.sortBy = "value";
        chart_third.fontSize = 10;

        let nodeTemplate =  chart_third.nodes.template;
        nodeTemplate.readerTitle = "Click to show/hide or drag to rearrange";
        nodeTemplate.showSystemTooltip = true;
        nodeTemplate.propertyFields.fill = "color";
        nodeTemplate.tooltipText = "{name}'s kisses: {total}";

        // when rolled over the node, make all the links rolled-over
        nodeTemplate.events.on("over", function(event) {
            let node = event.target;
            node.outgoingDataItems.each(function(dataItem) {
                if(dataItem.toNode){
                    dataItem.link.isHover = true;
                 //   dataItem.toNode.label.isHover = true;

                }
            })
            node.incomingDataItems.each(function(dataItem) {
                if(dataItem.fromNode){
                    dataItem.link.isHover = true;
                    dataItem.fromNode.label.isHover = true;
                }
            })

            node.label.isHover = true;
        })

        // when rolled out from the node, make all the links rolled-out
        nodeTemplate.events.on("out", function(event) {
            let node = event.target;
            node.outgoingDataItems.each(function(dataItem) {
                if(dataItem.toNode){
                    dataItem.link.isHover = false;
                  //  dataItem.toNode.label.isHover = false;
                }
            })
            node.incomingDataItems.each(function(dataItem) {
                if(dataItem.fromNode){
                    dataItem.link.isHover = false;
                 //  dataItem.fromNode.label.isHover = false;
                }
            })

            node.label.isHover = false;
        })

        let label = nodeTemplate.label;
        label.relativeRotation = 90;

        label.fillOpacity = 0.4;
        let labelHS = label.states.create("hover");
        labelHS.properties.fillOpacity = 1;

        nodeTemplate.cursorOverStyle = am4core.MouseCursorStyle.pointer;
        // this adapter makes non-main character nodes to be filled with color of the main character which he/she kissed most
        nodeTemplate.adapter.add("fill", function(fill, target) {
            let node = target;
            let counters = {};
            let mainChar = false;
            node.incomingDataItems.each(function(dataItem) {
                if(colors[dataItem.toName]){
                    mainChar = true;
                }

                if(isNaN(counters[dataItem.fromName])){
                    counters[dataItem.fromName] = dataItem.value;
                }
                else{
                    counters[dataItem.fromName] += dataItem.value;
                }
            })
            if(mainChar){
                return fill;
            }

            let count = 0;
            let color;
            let biggest = 0;
            let biggestName;

            for(var name in counters){
                if(counters[name] > biggest){
                    biggestName = name;
                    biggest = counters[name];
                }
            }
            if(colors[biggestName]){
                fill = colors[biggestName];
            }

            return fill;
        })

        // link template
        let linkTemplate =  chart_third.links.template;
        linkTemplate.strokeOpacity = 0;
        linkTemplate.fillOpacity = 0.15;
        linkTemplate.tooltipText = "{fromName} & {toName}:{value.value}";

        let hoverState = linkTemplate.states.create("hover");
        hoverState.properties.fillOpacity = 0.7;
        hoverState.properties.strokeOpacity = 0.7;

        // data credit label
        let creditLabel =  chart_third.chartContainer.createChild(am4core.TextLink);
        creditLabel.text = "Data source: notrudedude";
        creditLabel.url = "https://www.reddit.com/user/notrudedude";
        creditLabel.y = am4core.percent(99);
        creditLabel.x = am4core.percent(99);
        creditLabel.horizontalCenter = "right";
        creditLabel.verticalCenter = "bottom";

        let titleImage =  chart_third.chartContainer.createChild(am4core.Image);
        titleImage.href = "//www.amcharts.com/wp-content/uploads/2018/11/whokissed.png";
        titleImage.x = 30
        titleImage.y = 30;
        titleImage.width = 200;
        titleImage.height = 200;

      });

      this.zone.runOutsideAngular(() => {


        let chart_fourth = am4core.create("chartdiv_fourth", am4charts.XYChart);
        chart_fourth.data = [
          { year: "1896", uk: 7, ussr: 0, russia: 0, usa: 20, china: 0 },
          { year: "1900", uk: 78, ussr: 0, russia: 0, usa: 55, china: 0 },
          { year: "1904", uk: 2, ussr: 0, russia: 0, usa: 394, china: 0 },
          { year: "1908", uk: 347, ussr: 0, russia: 0, usa: 63, china: 0 },
          { year: "1912", uk: 160, ussr: 0, russia: 0, usa: 101, china: 0 },
          { year: "1916", uk: 0, ussr: 0, russia: 0, usa: 0, china: 0 },
          { year: "1920", uk: 107, ussr: 0, russia: 0, usa: 193, china: 0 },
          { year: "1924", uk: 66, ussr: 0, russia: 0, usa: 198, china: 0 },
          { year: "1928", uk: 55, ussr: 0, russia: 0, usa: 84, china: 0 },
          { year: "1932", uk: 34, ussr: 0, russia: 0, usa: 181, china: 0 },
          { year: "1936", uk: 36, ussr: 0, russia: 0, usa: 92, china: 0 },
          { year: "1940", uk: 0, ussr: 0, russia: 0, usa: 0, china: 0 },
          { year: "1944", uk: 0, ussr: 0, russia: 0, usa: 0, china: 0 },
          { year: "1948", uk: 56, ussr: 0, russia: 0, usa: 148, china: 0 },
          { year: "1952", uk: 31, ussr: 117, russia: 0, usa: 130, china: 0 },
          { year: "1956", uk: 45, ussr: 169, russia: 0, usa: 118, china: 0 },
          { year: "1960", uk: 28, ussr: 169, russia: 0, usa: 112, china: 0 },
          { year: "1964", uk: 28, ussr: 174, russia: 0, usa: 150, china: 0 },
          { year: "1968", uk: 18, ussr: 188, russia: 0, usa: 149, china: 0 },
          { year: "1972", uk: 29, ussr: 211, russia: 0, usa: 155, china: 0 },
          { year: "1976", uk: 32, ussr: 285, russia: 0, usa: 155, china: 0 },
          { year: "1980", uk: 45, ussr: 442, russia: 0, usa: 0, china: 0 },
          { year: "1984", uk: 72, ussr: 0, russia: 0, usa: 333, china: 76 },
          { year: "1988", uk: 53, ussr: 294, russia: 0, usa: 193, china: 53 },
          { year: "1992", uk: 50, ussr: 0, russia: 0, usa: 224, china: 83 },
          { year: "1996", uk: 26, ussr: 0, russia: 115, usa: 260, china: 110 },
          { year: "2000", uk: 55, ussr: 0, russia: 188, usa: 248, china: 79 },
          { year: "2004", uk: 57, ussr: 0, russia: 192, usa: 264, china: 94 },
          { year: "2008", uk: 77, ussr: 0, russia: 143, usa: 315, china: 184 }
        ];

        // Create axes
        let categoryAxis = chart_fourth.xAxes.push(new am4charts.CategoryAxis());
        categoryAxis.dataFields.category = "year";
        categoryAxis.renderer.grid.template.location = 0;
        categoryAxis.renderer.minGridDistance = 50;
        categoryAxis.startLocation = 0.5;
        categoryAxis.endLocation = 0.5;

        let valueAxis = chart_fourth.yAxes.push(new am4charts.ValueAxis());


        // Create series
        function createSeries(field, name) {
          let series = chart_fourth.series.push(new am4charts.LineSeries());
          series.dummyData = {
            field: field
          }
          series.dataFields.valueY = field + "_hi";
          series.dataFields.openValueY = field + "_low";
          series.dataFields.categoryX = "year";
          series.name = name;
          series.tooltipText = "[font-size: 18]{name}[/]\n{categoryX}: [bold]{" + field + "}[/]";
          series.strokeWidth = 1;
          series.fillOpacity = 1;
          series.tensionX = 0.8;

          return series;
        }

        createSeries("uk", "United Kingdom");
        createSeries("ussr", "Soviet Union");
        createSeries("russia", "Russia");
        createSeries("usa", "United States");
        createSeries("china", "China");

        // Legend
        chart_fourth.legend = new am4charts.Legend();
        chart_fourth.legend.itemContainers.template.togglable = false;
        chart_fourth.legend.itemContainers.template.cursorOverStyle = am4core.MouseCursorStyle.default;
        chart_fourth.legend.position = "right"
        chart_fourth.legend.reverseOrder = true;

        // Cursor
        chart_fourth.cursor = new am4charts.XYCursor();
        chart_fourth.cursor.maxTooltipDistance = 0;

        // Responsive
        chart_fourth.responsive.enabled = true;
        chart_fourth.responsive.useDefault = false;
        chart_fourth.responsive.rules.push({
          relevant: am4core.ResponsiveBreakpoints.widthL,
          state: function(target, stateId) {
            if (target instanceof am4charts.Legend) {
              let state = target.states.create(stateId);
              state.properties.position = "bottom";
              return state;
            }
            return null;
          }
        });

        // Prepare data for the river-stacked series
        chart_fourth.events.on("beforedatavalidated", updateData);
        function updateData() {

          let data = chart_fourth.data;
          if (data.length == 0) {
            return;
          }

          for (var i = 0; i < data.length; i++) {
            let row = data[i];
            let sum = 0;

            // Calculate open and close values
            chart_fourth.series.each(function(series) {
              let field = series.dummyData.field;
              let val = Number(row[field]);
              row[field + "_low"] = sum;
              row[field + "_hi"] = sum + val;
              sum += val;
            });

            // Adjust values so they are centered
            let offset = sum / 2;
            chart_fourth.series.each(function(series) {
              let field = series.dummyData.field;
              row[field + "_low"] -= offset;
              row[field + "_hi"] -= offset;
            });

          }

        }

      });

      this.zone.runOutsideAngular(()=>{

        let chart_fifth = am4core.create("chartdiv_fifth", am4charts.XYChart);
        let data = [];
let value = 50;
for(var i = 0; i < 300; i++){
  let date = new Date();
  date.setHours(0,0,0,0);
  date.setDate(i);
  value -= Math.round((Math.random() < 0.5 ? 1 : -1) * Math.random() * 10);
  data.push({date:date, value: value});
}

chart_fifth.data = data;

// Create axes
let dateAxis = chart_fifth.xAxes.push(new am4charts.DateAxis());
dateAxis.renderer.minGridDistance = 60;

let valueAxis = chart_fifth.yAxes.push(new am4charts.ValueAxis());

// Create series
let series = chart_fifth.series.push(new am4charts.LineSeries());
series.dataFields.valueY = "value";
series.dataFields.dateX = "date";
series.tooltipText = "{value}"

series.tooltip.pointerOrientation = "vertical";

chart_fifth.cursor = new am4charts.XYCursor();
chart_fifth.cursor.snapToSeries = series;
chart_fifth.cursor.xAxis = dateAxis;

//chart.scrollbarY = new am4core.Scrollbar();
chart_fifth.scrollbarX = new am4core.Scrollbar();



      });


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

