import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Client } from '../models/client';
import { Company } from '../models/company';
import { Person } from '../models/person';
import { ViaCep } from '../models/via-cep';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  
  url = 'http://localhost:8080/api/clients'
  urlCompany = 'http://localhost:8080/api/clients/company'
  urlPerson = 'http://localhost:8080/api/clients/person'
  

  constructor(private httpClient: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }

  getClients(): Observable<Client[]> {
    return this.httpClient.get<Client[]>(this.url)
      .pipe(
        retry(2),
        catchError(this.handleError))
  }
  

  
  
  saveCompany(company: Company): Observable<Company> {
    return this.httpClient.post<Company>(this.urlCompany, JSON.stringify(company), this.httpOptions)
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  savePerson(person: Person): Observable<Person> {
    return this.httpClient.post<Person>(this.urlPerson, JSON.stringify(person), this.httpOptions)
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  handleError(error: HttpErrorResponse) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      errorMessage = error.error.message;
    } else {
      errorMessage = `Código do erro: ${error.status}, ` + `menssagem: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(errorMessage);
  };
}
