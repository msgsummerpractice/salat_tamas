import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map, catchError, throwError, timeout } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ImageService {
  private apiUrl = 'https://dog.ceo/api/breeds/image/random';
  constructor(private http: HttpClient) {}

  fetchRandomDogImage(): Observable<string> {
    return this.http.get<{ message: string; status: string }>(this.apiUrl).pipe(
      timeout(5000),
      map((response) => response.message),
      catchError((error) => {
        console.error('Error fetching dog image:', error);
        return throwError(() => new Error('Failed to fetch dog image'));
      }),
    );
  }
}
