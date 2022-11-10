import {Component, Input} from '@angular/core';
import {LoaderService} from "../../../services/loaderService";

@Component({
  selector: 'app-fizzbuzz-list',
  templateUrl: './fizzbuzz-list.component.html',
  styleUrls: ['./fizzbuzz-list.component.css']
})
export class FizzbuzzListComponent {
  @Input() public fizzBuzzList: String[];

  public constructor() {}

}
