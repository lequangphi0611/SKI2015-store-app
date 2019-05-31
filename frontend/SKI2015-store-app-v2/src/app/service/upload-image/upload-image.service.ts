import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

interface ResponseUpload {
  relativePath: string;
}

@Injectable({
  providedIn: 'root'
})
export class UploadImageService {

  readonly URL_REQUEST = '/api/storage/upload';

  constructor(private http: HttpClient) { }

  upload(fb: FormData, fileName: string): Observable<ResponseUpload> {
    let filename = fileName.trim();
    while (filename.indexOf(" ") > -1) {
      filename = filename.replace(" ", "-");
    }
    return this.http.post<ResponseUpload>(this.URL_REQUEST, fb, {
      params: {
        fileName : filename
      }
    });
  }
}
