import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FizzbuzzListComponent } from './fizzbuzz-list.component';
import {CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";

describe('FizzbuzzListComponent', () => {
  let component: FizzbuzzListComponent;
  let fixture: ComponentFixture<FizzbuzzListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FizzbuzzListComponent ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FizzbuzzListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
