import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpErrorResponse
} from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';
import { finalize } from 'rxjs/operators';
import {LoaderService} from "./loaderService";
import {MdbModalRef, MdbModalService} from "mdb-angular-ui-kit/modal";
import {ModalComponent} from "../components/modal/modal.component";

@Injectable()
export class LoadingInterceptor implements HttpInterceptor {
  private totalRequests = 0;
  private modalRef: MdbModalRef<ModalComponent>

  constructor(private loadingService: LoaderService, private modalService: MdbModalService) {
  }

  public intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    this.totalRequests++;
    this.loadingService.setLoading(true);
    return next.handle(request).pipe(
      catchError(errorMessage => {
        this.openModal(errorMessage);
        return throwError(errorMessage);
      }),
      finalize(() => {
        this.totalRequests--;
        if (this.totalRequests == 0) {
          this.loadingService.setLoading(false);
        }
      })
    );
  }

  private openModal(errorMessage: HttpErrorResponse): void {
    this.modalRef = this.modalService.open(ModalComponent, {
      data: {modalContent: errorMessage.statusText},
    });
  }
}
