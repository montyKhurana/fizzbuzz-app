import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {catchError, map, Observable, of} from 'rxjs';
import {FizzBuzzResponse} from "../components/models/fizzbuzz.model";

const baseUrl = 'http://localhost:8080/fizzbuzz/play';

@Injectable({
  providedIn: 'root'
})
export class FizzbuzzService {

  constructor(private http: HttpClient) { }

  getFizzBuzzSequence(input?: number): Observable<String[]> {
    return this.http.get<FizzBuzzResponse>(`${baseUrl}/${input}`)
      .pipe(
        map(response => response.fizzBuzzSequence),
        catchError(error => {
          console.log('error is ', error);
          return of<String[]>([]);
        }),
      );
  }
}
