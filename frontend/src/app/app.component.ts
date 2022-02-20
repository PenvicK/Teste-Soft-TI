import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Client } from './models/client';
import { Company } from './models/company';
import { Person } from './models/person';
import { ClientService } from './services/client.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  client = {} as Client;
  clients: Client[] = [];
  company = {} as Company;
  person = {} as Person;


  constructor(private clientService: ClientService) {}
  
  ngOnInit() {
    this.getClients();
  }

  getClients() {
    this.clientService.getClients().subscribe((clients: Client[]) => {
      this.clients = clients;
    });
  }

  cleanForm(form: NgForm) {
    this.clientService.getClients();
    form.resetForm();
    this.company = {} as Company;
    this.person = {} as Person;
  }
}
