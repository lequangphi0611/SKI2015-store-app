import { CategoryService } from 'src/app/service/category.service';
import { Category } from './../../model/Category';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  readonly defaultImgSrc = "/assets/dashboard/img/default-avatar.png";

  categories: Category[] = [];

  imageUrl: any;

  constructor(
    private categoryService: CategoryService
  ) {
   }

  ngOnInit() {
    this.imageUrl = this.defaultImgSrc;
    this.fetchCategoties();
  }

  fetchCategoties(): void {
    this.categoryService.getCategories()
      .subscribe((data) => {
        this.categories = data;
      });
  }

  onSelectFile(file: File) {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = (event) => {
      this.imageUrl = reader.result;
    }
  }

}
