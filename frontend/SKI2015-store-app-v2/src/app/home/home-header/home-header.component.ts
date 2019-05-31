import { CategoryService } from './../../service/category.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home-header',
  templateUrl: './home-header.component.html',
  styleUrls: ['./home-header.component.css']
})
export class HomeHeaderComponent implements OnInit {

  categories = [];

  constructor(private categoryService: CategoryService) { }

  ngOnInit() {
    this.fetchCategories();
  }

  fetchCategories() {
    this.categoryService.getCategories()
      .subscribe((data) => {
        this.categories = data;
      });
  }
}
