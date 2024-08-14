import {Component, OnInit} from '@angular/core';
import {BreakpointService} from "../../services/breakpoint.service";
import {Breakpoint} from "../../core/model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";


@Component({
  selector: 'app-breakpoint-list',
  templateUrl: './breakpoint-list.component.html',
  styleUrls: ['./breakpoint-list.component.scss']
})
export class BreakpointListComponent implements OnInit {

  breakpoints: Breakpoint[] = [];
  addBreakpointForm!: FormGroup;

  constructor(private fb: FormBuilder, private breakpointService: BreakpointService) {
  }

  ngOnInit(): void {
    this.addBreakpointForm = this.fb.group({
      fileReference: ['', Validators.required],
      lineNumber: ['', Validators.required],
    });

    this.breakpointService.breakpoints$.subscribe(breakpoints => {
      this.breakpoints = breakpoints;
    });
    this.breakpointService.getBreakpoints();
  }


  onSubmit() {
    if (this.addBreakpointForm.invalid) {
      console.log('Form is invalid');
      return;
    }
    const fileReference = this.addBreakpointForm.get('fileReference')?.value;
    const lineNumber = this.addBreakpointForm.get('lineNumber')?.value;

    this.breakpointService.addBreakpoint(fileReference, lineNumber).subscribe();
  }
}
