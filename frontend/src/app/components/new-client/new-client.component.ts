import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Company } from 'src/app/models/company';
import { Person } from 'src/app/models/person';
import { ClientService } from 'src/app/services/client.service';

@Component({
  selector: 'app-new-client',
  templateUrl: './new-client.component.html',
  styleUrls: ['./new-client.component.css']
})
export class NewClientComponent {

  constructor(private clientService: ClientService) {}

  

}
