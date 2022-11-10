import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {FizzbuzzComponent} from "./components/fizzbuzz/fizzbuzz.component";

const routes: Routes = [
  { path: '', redirectTo: 'fizzbuzz', pathMatch: 'full' },
  { path: 'fizzbuzz', component: FizzbuzzComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

