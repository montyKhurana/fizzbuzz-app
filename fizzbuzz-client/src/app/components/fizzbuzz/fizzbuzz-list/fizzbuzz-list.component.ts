import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {LoaderService} from "../../../services/loaderService";
import {FizzBuzzResponse} from "../../models/fizzbuzz.model";

@Component({
  selector: 'app-fizzbuzz-list',
  templateUrl: './fizzbuzz-list.component.html',
  styleUrls: ['./fizzbuzz-list.component.css']
})
export class FizzbuzzListComponent implements OnInit{
  @Input() public inputNumber: number;
  @Input() public fizzBuzzResponse: FizzBuzzResponse
  @Input() public fizzBuzzList: String[];
  @Output() public pageChangeTo: EventEmitter<number> = new EventEmitter<number>();

  public resultsLimit;
  public currentPage = 1; // by default 1
  public totalItems ;

  public constructor() {}

  ngOnInit(): void {
    this.resultsLimit = this.fizzBuzzResponse.resultsLimit;
    this.currentPage = this.fizzBuzzResponse.currentPage;
    this.totalItems = this.fizzBuzzResponse.input;
  }

  // page  and size related info shud be sent to parent controller
  // because parent controller is making api call
  handlePageChange(event: number): void {
    this.currentPage = event;
    console.log('page no. changed to ', event);
    // send event
    this.pageChangeTo.emit(event);
  }

}
