import {NgModule} from "@angular/core";
import {FizzbuzzListComponent} from "./fizzbuzz-list/fizzbuzz-list.component";
import {LoadingComponent} from "./loading/loading.component";
import {FizzbuzzComponent} from "./fizzbuzz.component";
import {BrowserModule} from "@angular/platform-browser";
import {AppRoutingModule} from "../../app-routing.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {FlexLayoutModule} from "@angular/flex-layout";

const fizzBuzzComponents = [
  FizzbuzzComponent,
  FizzbuzzListComponent,
  LoadingComponent,
];
@NgModule({
  declarations: [...fizzBuzzComponents],
  entryComponents: [],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    FlexLayoutModule,
    ReactiveFormsModule,
  ],
  exports: [...fizzBuzzComponents],
})
export class FizzbuzzModule {
}
