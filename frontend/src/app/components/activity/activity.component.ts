import {Component, OnInit} from '@angular/core';
import {BreakpointService} from "../../services/breakpoint.service";
import {BreakpointHitResponse} from "../../core/model";

@Component({
  selector: 'app-activity',
  templateUrl: './activity.component.html',
  styleUrls: ['./activity.component.scss']
})
export class ActivityComponent implements OnInit {
  protected hit!: BreakpointHitResponse;
  protected jsonHit: any;

  constructor(private breakpointService: BreakpointService) {
  }

  ngOnInit(): void {
    this.breakpointService.getActiveBreakpoint().subscribe(result => {
      this.hit = result;
      this.jsonHit = JSON.stringify(this.hit, null, 2);
    });
  }


  protected readonly Object = Object;
}
