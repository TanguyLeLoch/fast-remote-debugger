import {Injectable} from '@angular/core';
import {Breakpoint, BreakpointControllerService} from "../core/model";
import {BehaviorSubject, tap} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BreakpointService {

  private breakpointsSubject = new BehaviorSubject<Breakpoint[]>([]);
  public breakpoints$ = this.breakpointsSubject.asObservable();


  constructor(private breakpointControllerService: BreakpointControllerService) {

  }

  getBreakpoints(): void {
    this.breakpointControllerService.getBreakpoints().pipe(
      tap(breakpoints => {
        this.breakpointsSubject.next(breakpoints);
      })
    ).subscribe()
  }

  addBreakpoint(fileReference: string, lineNumber: number) {
    return this.breakpointControllerService.addBreakpoint(fileReference, lineNumber).pipe(
      tap(() => {
        this.getBreakpoints();
      })
    );
  }

  getActiveBreakpoint() {
    return this.breakpointControllerService.getHits();
  }
}
