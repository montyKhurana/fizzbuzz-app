import {TestBed} from '@angular/core/testing';

import { FizzbuzzService } from './fizzbuzz.service';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";
import {of} from "rxjs";

describe('FizzbuzzService', () => {
  let service: FizzbuzzService;
  let httpMock: HttpTestingController;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA]
    }).compileComponents();
    service = TestBed.inject(FizzbuzzService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('getFizzBuzzSequence should use GET to retrieve data', () => {
    service.getFizzBuzzSequence(10).subscribe();
    const testRequest = httpMock.expectOne('http://localhost:8080/fizzbuzz/play/10');
    expect(testRequest.request.method).toEqual('GET');
  });

  it('should get correct fizzbuzz sequence', () => {
    const fizzBuzzSequence = ["1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz"];
    service.getFizzBuzzSequence(10).subscribe(value => {
      expect(value).toEqual(fizzBuzzSequence);
    })

    const request = httpMock.expectOne('http://localhost:8080/fizzbuzz/play/10');
    request.flush({input:"10", fizzBuzzSequence:fizzBuzzSequence});
  });
});
