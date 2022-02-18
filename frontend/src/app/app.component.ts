import { Component, OnInit } from '@angular/core';
import { Client } from './models/client';
import { ClientService } from './services/client.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  client = {} as Client;
  clients: Client[] = [];

  constructor(private clientService: ClientService) {}
  
  ngOnInit() {
    this.getClients();
  }

  getClients() {
    this.clientService.getClients().subscribe((clients: Client[]) => {
      this.clients = clients;
    });
    console.log(this.clients);
  }
}
