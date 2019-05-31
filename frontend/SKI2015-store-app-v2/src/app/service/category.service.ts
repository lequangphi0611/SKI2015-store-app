import { Product } from './../model/Product';
import { Category } from './../model/Category';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  readonly URL_REQUEST = `/api/categories`;

  constructor(private http: HttpClient) { }

  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(this.URL_REQUEST);
  }

  getAllproductsById(id: number, query?: string): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.URL_REQUEST}/${id}/products`, {
      params : {
        q : query ? query : ''
      }
    });
  }

  getCategory(id: number): Observable<Category> {
    return this.http.get<Product>(`${this.URL_REQUEST}/${id}`);
  }

  createCategory(category: Category): Observable<Category> {
    return this.http.post<Category>(this.URL_REQUEST, {name : category.name});
  }

  updateCategory(category: Category): Observable<Category> {
    return this.http.put<Category>(`${this.URL_REQUEST}/${category.id}`, {name : category.name});
  }

  delete(id: number): Observable<any> {
    return this.http.delete<any>(`${this.URL_REQUEST}/${id}`);
  }

}
