import { Injectable } from '@angular/core';

import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Part } from './part';
import { MessageService } from './message.service';
import { catchError, map, tap } from 'rxjs/operators';
import { LoginComponent } from './login/login.component';
import { Login } from './login';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private loginurl = 'http://localhost:8080/login';
  public isAdmin : boolean;
  private find = 'find';


  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient, private messageService: MessageService) { 
    this.isAdmin = false;
  }
  
  private log(message: string) {
    this.messageService.add(`HeroService: ${message}`);
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
        console.error(error); // log to console instead
        this.log(`${operation} failed: ${error.message}`);
        return of(result as T);
    };
  }

  getLogin(username: string, password: string): Observable<boolean>{
    console.log(this.http.get<boolean>(`${this.loginurl}/${username}/${password}`))
    console.log(`${this.loginurl}/${username}/${password}`)
    return this.http.get<boolean>(`${this.loginurl}/${username}/${password}`)
      .pipe(
        tap(_ => this.log('fetched heroes')),
        tap(isAdmin => this.isAdmin = isAdmin),
        catchError(this.handleError<boolean>('getHeroes'))
      );
  }

  findLogin(user: string, pass: string, find: string): Observable<Login> {
    console.log(this.http.get<Login>(`${this.loginurl}/${find}/${user}/${pass}`))
    console.log(`${this.loginurl}/${find}/${user}/${pass}`)
    return this.http.get<Login>(`${this.loginurl}/${this.find}/${user}/${pass}`);
  }
  
  



}
