import { Product } from './../../model/Product';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  readonly URL_REQUEST = '/api/products';

  constructor(private http: HttpClient) { }

  getProducts(query?: string): Observable<Product[]> {
    return this.http.get<Product[]>(this.URL_REQUEST, {
      params : {
        q : query ? query : ''
      }
    });
  }

  getProduct(id: number): Observable<Product> {
    return this.http.get<Product>(`${this.URL_REQUEST}/${id}`);
  }

  insertProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(this.URL_REQUEST, product);
  }

  updateProduct(product: Product): Observable<Product> {
    return this.http.put<Product>(`${this.URL_REQUEST}/${product.id}`, product);
  }

  deleteProduct(id: number): Observable<any> {
    return this.http.delete<any>(`${this.URL_REQUEST}/${id}`);
  }

}
