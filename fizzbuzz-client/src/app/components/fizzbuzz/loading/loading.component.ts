import {Component} from "@angular/core";
import {LoaderService} from "../../../services/loaderService";

@Component({
  selector: 'app-loading-fizzbuzz',
  templateUrl: './loading.component.html',
  styleUrls: ['./loading.component.css']
})
export class LoadingComponent {

  public constructor(public loader: LoaderService) { }
}
