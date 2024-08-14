import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {ConnectionComponent} from "./components/connection/connection.component";
import {ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {BreakpointListComponent} from "./components/breakpoint-list/breakpoint-list.component";
import {ActivityComponent} from "./components/activity/activity.component";
import { VariableItemComponent } from './components/variable-item/variable-item.component';

@NgModule({
  declarations: [
    AppComponent,
    ConnectionComponent,
    BreakpointListComponent,
    ActivityComponent,
    VariableItemComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
