import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ConnectionService} from "../../services/connection.service";
import {tap} from "rxjs";

@Component({
  selector: 'app-connection',
  templateUrl: './connection.component.html',
  styleUrls: ['./connection.component.scss']
})
export class ConnectionComponent implements OnInit {
  connectForm!: FormGroup;

  protected isConnected!: boolean;


  constructor(private fb: FormBuilder, private connectionService: ConnectionService) {
  }

  ngOnInit(): void {
    this.connectForm = this.fb.group({
      host: ['', Validators.required],
      port: ['', [Validators.required, Validators.min(1), Validators.max(65535)]]
    });
    this.connectionService.isConnectionEstablished().subscribe(isConnected => {
      this.isConnected = isConnected
    });
    this.connectionService.isConnected$.subscribe(isConnected => this.isConnected = isConnected);
  }


  onSubmit(): void {
    if (this.connectForm.valid) {
      const host = this.connectForm.get('host')?.value;
      const port = this.connectForm.get('port')?.value;


      this.connectionService.connect(host, port)
        .pipe(
          tap(response => {
            this.isConnected = true;
          })
        )
        .subscribe();
    } else {
      console.log('Form is invalid');
    }
  }

  onDisconnect() {
    this.connectionService.disconnect().subscribe();
  }
}
