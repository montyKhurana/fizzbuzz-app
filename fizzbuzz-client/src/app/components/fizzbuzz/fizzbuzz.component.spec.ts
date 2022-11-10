import {ComponentFixture, TestBed} from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import {FizzbuzzComponent} from "./fizzbuzz.component";
import {FormBuilder, ReactiveFormsModule} from "@angular/forms";
import {FizzbuzzService} from "../../services/fizzbuzz.service";
import {CUSTOM_ELEMENTS_SCHEMA, DebugElement} from "@angular/core";
import {of} from "rxjs";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {CommonModule} from "@angular/common";

describe('FizzBuzzComponent', () => {
  let fizzBuzzComponent: FizzbuzzComponent;
  let fixture: ComponentFixture<FizzbuzzComponent>;
  // create new instance of FormBuilder
  let formBuilder: FormBuilder = new FormBuilder();
  let http: HttpClientTestingModule;
  let fizzbuzzService: FizzbuzzService;
  let element: DebugElement;
  let mockFizzBuzzSequence: String[];
  let spy: jasmine.Spy;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        CommonModule,
        ReactiveFormsModule,
        RouterTestingModule,
        HttpClientTestingModule,
      ],
      providers:[
        FizzbuzzService,
        FormBuilder,
      ],
      declarations: [
        FizzbuzzComponent,
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FizzbuzzComponent);
    fizzBuzzComponent = fixture.componentInstance;
    http = TestBed.inject(HttpClientTestingModule);
    fizzbuzzService = TestBed.inject(FizzbuzzService);
    formBuilder = TestBed.inject(FormBuilder);
    element = fixture.debugElement;

    mockFizzBuzzSequence = ["1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz"];
    spy = spyOn(fizzbuzzService, 'getFizzBuzzSequence').and.returnValue(of(mockFizzBuzzSequence));

    // pass in the form dynamically
    fizzBuzzComponent.initFizzbuzzForm();
    fixture.autoDetectChanges(true);
  });

  it('should create the FizzbuzzComponent', () => {
    expect(fizzBuzzComponent).toBeTruthy();
  });

  it('should have a text input and Play button', () => {
    const formElement = element.nativeElement.querySelector('#fizzbuzzForm')
    const inputElements = formElement.querySelectorAll('input');
    const buttonElements = formElement.querySelectorAll('button');
    const fizzListElements = element.nativeElement.querySelector('#fizzbuzz-container').querySelectorAll('app-fizzbuzz-list');
    expect(inputElements.length).toEqual(1);
    expect(buttonElements.length).toEqual(1);
    expect(fizzListElements.length).toEqual(0);
  });

  it('check input box value before entering some value and validation', () => {
    const formElement = element.nativeElement.querySelector('#fizzbuzzForm')
    const inputBox = formElement.querySelectorAll('input')[0];
    const inputControlFromGroup = fizzBuzzComponent.fizzBuzzForm.get('inputControl')
    expect(inputBox.value).toEqual(inputControlFromGroup.value);
    expect(inputControlFromGroup.errors).not.toBeNull();
    expect(inputControlFromGroup.errors['required']).toBeTruthy();
  });

  it('should have play button enabled after setting valid value in input box', () => {
    expect(fizzBuzzComponent.fizzBuzzForm.valid).toBeFalsy();
    fizzBuzzComponent.fizzBuzzForm.get('inputControl').setValue('123');
    expect(fizzBuzzComponent.fizzBuzzForm.valid).toBeTruthy();
  });

  it('should have play button disabled after setting invalid value in input box', () => {
    expect(fizzBuzzComponent.fizzBuzzForm.valid).toBeFalsy();
    fizzBuzzComponent.fizzBuzzForm.get('inputControl').setValue('abc');
    expect(fizzBuzzComponent.fizzBuzzForm.valid).toBeFalsy();
    const inputControlFromGroup = fizzBuzzComponent.fizzBuzzForm.get('inputControl')
    expect(inputControlFromGroup.errors).not.toBeNull();
    expect(inputControlFromGroup.errors['pattern']).toBeTruthy();
  });

  it('should successfully submit the play button on clicking the Save button and fizz list element is visible', async () => {
    expect(fizzBuzzComponent.fizzBuzzForm.valid).toBeFalsy();
    fizzBuzzComponent.fizzBuzzForm.get('inputControl').setValue('10');
    fixture.detectChanges();
    expect(fizzBuzzComponent.fizzBuzzForm.valid).toBeTruthy();

    const formElement = element.nativeElement.querySelector('#fizzbuzzForm')
    const playButton = formElement.querySelectorAll('button')[0];
    playButton.click();
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      fixture.detectChanges();
      expect(spy.calls.any()).toBeTruthy();
      const fizzListElements = element.nativeElement.querySelector('#fizzbuzz-container').querySelectorAll('app-fizzbuzz-list');
      expect(fizzListElements.length).toEqual(1);
    });
  })
});
