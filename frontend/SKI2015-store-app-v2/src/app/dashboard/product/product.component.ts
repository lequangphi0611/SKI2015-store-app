import { ImageChooseComponent } from './../image-choose/image-choose.component';

import { Observable } from 'rxjs';
import { UploadImageService } from './../../service/upload-image/upload-image.service';
import { FormGroup, FormBuilder, NgForm, Validators } from '@angular/forms';
import { ProductService } from './../../service/product/product.service';
import { Product } from './../../model/Product';
import { CategoryService } from 'src/app/service/category.service';
import { Category } from './../../model/Category';
import { Component, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  @ViewChild(ImageChooseComponent) imageChooseComponent: ImageChooseComponent;

  categories: Observable<Category[]>;

  products: Observable<Product[]>;

  product: Product;

  imageSelected: File;

  formProduct: FormGroup;

  editMode = false;

  constructor(
    private categoryService: CategoryService,
    private productService: ProductService,
    private fb: FormBuilder,
    private uploadImageService: UploadImageService
  ) {
  }

  ngOnInit() {
    this.fetchCategoties();
    this.fetchProducts();
    this.initForm();
  }

  buildProduct(): Product {
    const product = new Product();
    product.category = new Category();
    product.price = 0;
    const initCategorySelected = setInterval(() => {
      if (this.categories) {
        this.categories
          .subscribe((categories) => {
            product.category = categories[0];
            clearInterval(initCategorySelected);
          });
      }
    }, 500);
    return product;
  }

  initForm(): void {
    this.formProduct = this.fb.group({
      name: ['', Validators.required],
      categoryId: ['', Validators.required],
      price: ['', [Validators.required, Validators.min(0)]],
      description: ['']
    });

    this.initEditMode({ enableEditMode: false, product: this.buildProduct() });
  }

  initEditMode({ enableEditMode, product }) {
    this.product = product;
    this.editMode = enableEditMode;
    if(!enableEditMode) {
      this.imageChooseComponent.reset();
    }
  }

  get name() {
    return this.formProduct.get('name');
  }

  get categoryId() {
    return this.formProduct.get('categoryId');
  }

  get price() {
    return this.formProduct.get('price');
  }

  get description() {
    return this.formProduct.get('description');
  }

  fetchCategoties(): void {
    this.categories = this.categoryService.getCategories();
  }

  fetchProducts(): void {
    this.products = this.productService.getProducts();
  }

  onSelectFile(file: File) {
    this.imageSelected = file;
  }

  getProductFromFormGroup(): Product {
    return {
      name: this.name.value,
      category: { id: this.categoryId.value },
      manufactureDate: new Date(),
      price: this.price.value,
      description: this.description.value
    };
  }

  async onSubmit(myForm: NgForm) {
    const product = this.getProductFromFormGroup();
    if (this.editMode) {
      product.imagePath = this.imageSelected
          ? await this.uploadFile(product.name)
          : this.product.imagePath;
      product.id = this.product.id;
      await this.productService.updateProduct(product).toPromise();
    } else {
      product.imagePath = await this.uploadFile(product.name);
      await this.productService.insertProduct(product).toPromise();
    }

    this.fetchProducts();
    myForm.resetForm();
    this.initEditMode({
      enableEditMode : false,
      product : this.buildProduct()
    });
  }

  uploadFile(filename: string): Promise<string> {
    return new Promise<string>((resolve, reject) => {
      const fd = new FormData();
      fd.append('file', this.imageSelected);
      this.uploadImageService.upload(fd, filename)
        .subscribe((response) => {
          resolve(response.relativePath);
        },
          (err) => {
            reject(err);
          });
    });
  }

  enableEditMode(product: Product) {
    this.initEditMode({
      enableEditMode: true,
      product
    });
    this.imageChooseComponent.setImageUrl(product.imagePath);
    this.imageChooseComponent.imageName.next(product.imagePath.substring(product.imagePath.lastIndexOf('/') + 1, product.imagePath.length));
  }

  reset() {
    this.initEditMode({
      enableEditMode: false,
      product: this.buildProduct()
    });
  }

  delete(id: number) {
    this.productService.deleteProduct(id)
      .subscribe({
        complete : () => {
          this.fetchProducts();
        }
      });
  }

}
