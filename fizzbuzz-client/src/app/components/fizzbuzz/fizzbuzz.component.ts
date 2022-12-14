import {Component, OnInit} from '@angular/core';
import { FizzbuzzService } from 'src/app/services/fizzbuzz.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {LoaderService} from "../../services/loaderService";

@Component({
  selector: 'app-fizzbuzz',
  templateUrl: './fizzbuzz.component.html',
  styleUrls: ['./fizzbuzz.component.css']
})
export class FizzbuzzComponent implements OnInit {
  public fizzBuzzForm: FormGroup;
  public fizzBuzzList: String[];
  public userInput: number;
  private maxInputNumber: number = 20000;

  public constructor(private fizzbuzzService: FizzbuzzService,
                     private formBuilder: FormBuilder) { }

  public ngOnInit(): void {
    this.initFizzbuzzForm();
  }

  public get inputControl() {
    return this.fizzBuzzForm?.get('inputControl')!;
  }

  public playFizzBuzz(): void {
    const formValue = this.fizzBuzzForm.value;
    this.userInput = formValue.inputControl
    this.fizzBuzzList = [];
    this.fizzbuzzService.getFizzBuzzSequence(this.userInput)
      .subscribe({
        next: (data) => {
          this.fizzBuzzList = data;
        }});
  }

  initFizzbuzzForm(): void {
    this.fizzBuzzForm = this.formBuilder.group({
      inputControl: ['', [Validators.required,
        Validators.min(0),
        Validators.max(this.maxInputNumber),
        Validators.pattern(/^[1-9]\d*$/)]],
    });
  }

}
