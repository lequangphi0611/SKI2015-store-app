import { Admin } from './../../model/Admin';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  readonly URL_REQUEST = '/api/admins';

  constructor(private http: HttpClient) { }

  getAdmins(): Observable<Admin[]> {
    return this.http.get<Admin[]>(this.URL_REQUEST);
  }

  getAdminBy(username: string): Observable<Admin> {
    return this.http.get<Admin>(`${this.URL_REQUEST}/${username}`);
  }

  insertAdmin(admin: Admin): Observable<Admin> {
    return this.http.post<Admin>(this.URL_REQUEST, admin);
  }

  updateAdmin(admin: Admin): Observable<Admin> {
    return this.http.put<Admin>(`${this.URL_REQUEST}/${admin.id}`, admin);
  }

  deleteAdminBy(id: number): Observable<any> {
    return this.http.delete<any>(`${this.URL_REQUEST}/${id}`);
  }

}
