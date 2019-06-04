import { Component, OnInit, EventEmitter, Output, ViewChild, ElementRef } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-image-choose',
  templateUrl: './image-choose.component.html',
  styleUrls: ['./image-choose.component.css']
})
export class ImageChooseComponent implements OnInit {

  @ViewChild('inputFile') inputFile: ElementRef;

  defaultImgSrc = "/assets/dashboard/img/catalog-default-img.gif.png";

  private imageUrl: BehaviorSubject<any> = new BehaviorSubject(this.defaultImgSrc);

  readonly defaultMessage = 'Please select an image';

  imageUrl$ = this.imageUrl.asObservable();

  imageName: BehaviorSubject<string> = new BehaviorSubject(this.defaultMessage);

  imageName$ = this.imageName.asObservable();

  @Output() onChangeImage: EventEmitter<File> = new EventEmitter<File>();

  constructor() { }

  ngOnInit() {
  }

  onSelectFile(file: File) {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = (event) => {
      this.imageUrl.next(reader.result);
    };

    this.imageName.next(file.name);
    this.onChangeImage.emit(file);
  }

  reset() {
    this.imageUrl.next(this.defaultImgSrc);
    this.inputFile.nativeElement.value = null;
    this.imageName.next(this.defaultMessage);
  }

  public setImageUrl(imageUrl: any) {
    this.imageUrl.next(imageUrl);
  }

}
