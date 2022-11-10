import { NgModule } from '@angular/core';
import {HTTP_INTERCEPTORS} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {LoadingInterceptor} from "./services/loading-interceptor";
import {ModalComponent} from "./components/modal/modal.component";
import {MdbModalService} from "mdb-angular-ui-kit/modal";
import {Overlay} from "@angular/cdk/overlay";
import {FizzbuzzModule} from "./components/fizzbuzz/fizzbuzz.module";

@NgModule({
  declarations: [
    AppComponent,
    ModalComponent,
  ],
    imports: [
      FizzbuzzModule,
      AppRoutingModule,
    ],
  providers: [
    MdbModalService,
    Overlay,
    {
      provide: HTTP_INTERCEPTORS, useClass: LoadingInterceptor, multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
