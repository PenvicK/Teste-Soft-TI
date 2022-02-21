import { getLocaleDayPeriods, JsonPipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { EMPTY } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { CEPError } from 'src/app/models/ceperror';
import { CEPErrorCode } from 'src/app/models/ceperror-code';
import { Company, TypeClient } from 'src/app/models/company';
import { Person } from 'src/app/models/person';
import { ViaCep } from 'src/app/models/via-cep';
import { ClientService } from 'src/app/services/client.service';

@Component({
  selector: 'app-new-client',
  templateUrl: './new-client.component.html',
  styleUrls: ['./new-client.component.css']
})
export class NewClientComponent{
  company = {} as Company;
  person = {} as Person;
  viaCep?: ViaCep | null;
  cep = "";
  legalOrNatural: boolean = true;
  error = false;
  errorMessage = '';
  

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
  buscarCep(): void {
    this.clientService.buscarPorCep(this.company.cep || this.person.cep).pipe(
      catchError((erro: CEPError) => {
        this.error = true;

        switch (erro.getCode()) {
          case CEPErrorCode.CEP_VAZIO:
            this.errorMessage = 'Por favor, informe o CEP :)';
            break;
          case CEPErrorCode.CEP_INVALIDO:
            this.errorMessage = `O CEP "${this.cep}" não é válido :/`;
            break;
          case CEPErrorCode.CEP_MUITO_CURTO:
            this.errorMessage = 'O CEP informado é curto demais :P';
            break;
          case CEPErrorCode.CEP_MUITO_LONGO:
            this.errorMessage = 'O CEP informado é longo demais ¬¬';
            break;
          case CEPErrorCode.CEP_NAO_ENCONTRADO:
            this.errorMessage = `O CEP "${this.cep}" não existe :(`;
            break;
          default:
            this.errorMessage = 'Erro ao buscar o CEP :O';
        }

        return EMPTY;
      })
    ).subscribe((viaCep: ViaCep) => {
      this.viaCep = viaCep;
      if(typeof this.viaCep?.cep === "string"){
        this.populaForm(this.viaCep);
      }
    });
    
    
  }
  populaForm(viaCep: ViaCep){
    this.company.cep = viaCep.cep;
    this.person.cep = viaCep.cep;
    
    this.company.address = viaCep.logradouro;
    this.person.address = viaCep.logradouro;
   
    this.company.district = viaCep.bairro;
    this.person.district = viaCep.bairro;
    
    this.company.city = viaCep.localidade;
    this.person.city = viaCep.localidade;
  }

}

