import { Category } from 'src/app/model/Category';
import { Observable, Subscription } from 'rxjs';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { CategoryService } from 'src/app/service/category.service';
import { FormBuilder, FormGroup, FormControl, Validators, NgForm } from '@angular/forms';
import { tap, map } from 'rxjs/operators';

@Component({
  selector: 'app-category-dashboard',
  templateUrl: './category-dashboard.component.html',
  styleUrls: ['./category-dashboard.component.css']
})
export class CategoryDashboardComponent implements OnInit, OnDestroy {

  categories: {category: Category, productCount: Observable<number>}[];

  formCategory: FormGroup;

  editMode: boolean;

  category: Category;

  subscription: Subscription;

  constructor(
    private categoryService: CategoryService,
    private fb: FormBuilder
  ) {
    this.formCategory = this.fb.group({
      name : new FormControl('', Validators.required)
    });
    this.init();
   }

  ngOnInit() {
    this.fetchCategories();
  }

  fetchCategories() {
    this.categories = [];
    this.subscription = this.categoryService.getCategories()
      .subscribe(categories => {
        categories.forEach(category => {
          const productCount = this.categoryService.getAllproductsById(category.id)
            .pipe(
              map(products => products.length)
            );
          this.categories.push({category, productCount});
        });
      });
  }

  reset(): void {
    this.init();
    this.fetchCategories();
  }

  async onSubmit(myForm: NgForm) {
    const category: Category = {name : this.formCategory.value.name};
    if (!this.editMode) {
      await this.categoryService.createCategory(category).toPromise();
    } else {
      await this.categoryService.updateCategory({
        id : this.category.id,
        name : category.name
      }).toPromise();
    }
    this.reset();
    myForm.resetForm();
  }

  delete(id: number): void {
    this.categoryService.delete(id)
      .subscribe(() => {
        this.fetchCategories();
      });
  }

  enableEditMode(category: Category) {
    this.category = category;
    this.editMode = true;
  }

  init() {
    this.category = new Category();
    this.editMode = false;
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

}
