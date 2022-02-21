import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { retry, catchError, switchMap, map } from 'rxjs/operators';
import { Client } from '../models/client';
import { Company } from '../models/company';
import { Person } from '../models/person';
import { ViaCep } from '../models/via-cep';
import { validarCEP, validarEndereco } from '../utils';
import { BASE_URL } from '../models/constantes';
import { CEPError } from '../models/ceperror';
import { CEPErrorCode } from '../models/ceperror-code';

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
  
  buscarPorCep(cep: string): Observable<ViaCep> {
    return of(cep).pipe(
      validarCEP(),
      switchMap((cepValido) =>
        this.httpClient.get<ViaCep>(`${BASE_URL}/${cepValido}/json`)
      ),
      map((endereco) => {
        if ('cep' in endereco) {
          return endereco;
        }
        throw new CEPError(CEPErrorCode.CEP_NAO_ENCONTRADO);
      })
    );
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
