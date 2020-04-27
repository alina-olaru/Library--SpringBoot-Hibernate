import {
  Component,
  OnInit,
  ViewChild,
  ElementRef,
  Renderer2,
} from "@angular/core";

@Component({
  selector: "app-add-book-via-ocr",
  templateUrl: "./add-book-via-ocr.component.html",
  styleUrls: ["./add-book-via-ocr.component.scss"],
})
export class AddBookViaOCRComponent implements OnInit {
  state: number;
  @ViewChild("video", { static: true }) videoElement: ElementRef;
  @ViewChild("canvas", { static: true }) canvas: ElementRef;

  videoWidth = 100;
  videoHeight = 100;
  constraints = {
    video: {
      facingMode: "environment",
      width: { ideal: 4096 },
      height: { ideal: 2160 },
    },
  };

  constructor(private renderer: Renderer2) {
    this.state = 0;
  }

  ngOnInit(): void {
    console.log(this.state + " state");
  }
  ngOnDestroy(): void {
    //Called once, before the instance is destroyed.
    //Add 'implements OnDestroy' to the class.
    this.state = 0;
    console.log(this.state + " state");
  }

  startCamera() {
    console.log(this.state + " state");
    this.state = 1;
    console.log(this.state + " state");
    if (!!(navigator.mediaDevices && navigator.mediaDevices.getUserMedia)) {
      navigator.mediaDevices
        .getUserMedia(this.constraints)
        .then(this.attachVideo.bind(this))
        .catch(this.handleError);
    } else {
      alert("Sorry, camera not available.");
    }
  }

  attachVideo(stream) {
    console.log(this.state + " state");
    this.renderer.setProperty(
      this.videoElement.nativeElement,
      "srcObject",
      stream
    );
    this.renderer.listen(this.videoElement.nativeElement, "play", (event) => {
      this.videoHeight = this.videoElement.nativeElement.videoHeight;
      this.videoWidth = this.videoElement.nativeElement.videoWidth;
    });
  }

  capture() {
    console.log(this.state + " state");
    this.renderer.setProperty(
      this.canvas.nativeElement,
      "width",
      this.videoWidth
    );
    this.renderer.setProperty(
      this.canvas.nativeElement,
      "height",
      this.videoHeight
    );
    this.canvas.nativeElement
      .getContext("2d")
      .drawImage(this.videoElement.nativeElement, 0, 0);
  }

  handleError(error) {
    console.error("EROAREEEEEEEEEEEEEEEEEE");
    console.log("Error: ", error);
  }
}
