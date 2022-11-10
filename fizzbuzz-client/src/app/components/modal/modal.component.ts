import {MdbModalRef} from "mdb-angular-ui-kit/modal";
import {Component, Input} from "@angular/core";

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
})
export class ModalComponent {
  @Input() public modalContent:String;

  constructor(public modalRef: MdbModalRef<ModalComponent>) {}
}
