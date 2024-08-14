import {Injectable} from '@angular/core';
import {BehaviorSubject, map, Observable, of} from 'rxjs';
import {catchError, tap} from 'rxjs/operators';
import {ConnectionControllerService} from "../core/model";

@Injectable({
  providedIn: 'root'
})
export class ConnectionService {

  private isConnectedSubject = new BehaviorSubject<boolean>(false);
  public isConnected$ = this.isConnectedSubject.asObservable();

  constructor(private connectionController: ConnectionControllerService) {
  }

  connect(hostname: string, port: number): Observable<string> {

    return this.connectionController.connect(hostname, port).pipe(
      tap(() => {
        this.checkConnectionStatus();
      }),
      catchError(error => {
        console.error('Error connecting:', error);
        return of('ko');
      })
    );
  }

  isConnectionEstablished(): Observable<boolean> {
    return this.connectionController.isConnected().pipe(
      map(response => response.success),
    );
  }

  checkConnectionStatus() {
    this.isConnectionEstablished().pipe(
      tap(isConnected => {
        console.log('isConnected', isConnected);
        this.isConnectedSubject.next(isConnected)
      })
    ).subscribe();
  }

  disconnect(): Observable<any> {
    return this.connectionController.disconnect().pipe(
      tap(() => {
        this.checkConnectionStatus();
      })
    );
  }
}
