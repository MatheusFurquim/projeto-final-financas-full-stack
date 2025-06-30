import { Injectable } from '@angular/core';
import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from "rxjs/operators";
import { NotificationService } from './notification.service';

@Injectable()
export class HttpErrorInterceptor implements HttpInterceptor {

  constructor(private notificationService: NotificationService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    this.notificationService.clearError();

    return next
      .handle(request)
      .pipe(
        tap((event) => {
          if (event instanceof HttpResponse) {
            this.notificationService.clearError();
          }
        }),
        catchError((error: HttpErrorResponse) => {
          this.notificationService.showError(`Erro: ${error.error}`);
          return throwError(() => new Error(error.error));
        })
      )
  }
}
