import { getLocaleDayPeriods } from '@angular/common';
import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Company, TypeClient } from 'src/app/models/company';
import { Person } from 'src/app/models/person';
import { ViaCep } from 'src/app/models/via-cep';
import { ClientService } from 'src/app/services/client.service';

@Component({
  selector: 'app-new-client',
  templateUrl: './new-client.component.html',
  styleUrls: ['./new-client.component.css']
})
export class NewClientComponent {
  company = {} as Company;
  person = {} as Person;
  viaCep?: ViaCep | null;
  cep = "";
  legalOrNatural: boolean = true;

  constructor(private clientService: ClientService) {}

  saveCompany() {
    console.log(this.company.typeClient);
    this.company.typeClient = TypeClient.JURIDICA;  
    this.clientService.saveCompany(this.company).subscribe(() => {
      });
    window.location.reload();
  }

  savePerson() {
    this.person.typeClient = TypeClient.FISICA; 
    this.clientService.savePerson(this.person).subscribe(() => {
      });
       window.location.reload();
  }
  

  changeType(bool: string){
    console.log(this.legalOrNatural);
    if(bool == "false"){
      this.legalOrNatural = false
    }
    if(bool == "true"){
      this.legalOrNatural = true;
    }
  }

}
