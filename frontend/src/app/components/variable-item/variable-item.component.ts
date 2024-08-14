import {Component, Input, OnInit} from '@angular/core';
import {Variable} from "../../core/model";

@Component({
  selector: 'app-variable-item',
  templateUrl: './variable-item.component.html',
  styleUrls: ['./variable-item.component.scss']
})
export class VariableItemComponent implements OnInit {
  @Input() variable!: Variable;

  protected type!: string;
  protected isFolded = true;

  constructor() {
  }

  ngOnInit(): void {
    this.type = this.variable.type;
  }

  unfold() {
    this.isFolded = !this.isFolded;
  }
}
