import { Injectable } from '@angular/core';

import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Part } from './part';
import { MessageService } from './message.service';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class PartService {

  private partsUrl = 'http://localhost:8080/parts';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient, private messageService: MessageService) { }
  
  private log(message: string) {
    this.messageService.add(`PartService: ${message}`);
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
        console.error(error); // log to console instead
        this.log(`${operation} failed: ${error.message}`);
        return of(result as T);
    };
  }

  getParts(): Observable<Part[]> {
    return this.http.get<Part[]>(this.partsUrl)
      .pipe(
        tap(_ => this.log('fetched parts')),
        catchError(this.handleError<Part[]>('getParts', []))
      );
  }  

  getPart(id: number): Observable<Part> {
    const url = `${this.partsUrl}/${id}`;
    return this.http.get<Part>(url).pipe(
      tap(_ => this.log(`fetched part id=${id}`)),
      catchError(this.handleError<Part>(`getPart id=${id}`))
    );
  }

  searchParts(term: string): Observable<Part[]> {
    if (!term.trim()) {
      return of([]);
    }
    return this.http.get<Part[]>(`${this.partsUrl}/?name=${term}`).pipe(
      tap(x => x.length ?
         this.log(`found parts matching "${term}"`) :
         this.log(`no parts matching "${term}"`)),
      catchError(this.handleError<Part[]>('searchParts', []))
    );
  }
  
  updatePart(part: Part): Observable<any> {
    return this.http.put(this.partsUrl, part, this.httpOptions).pipe(
      tap(_ => this.log(`updated part id=${part.id}`)),
      catchError(this.handleError<any>('updatePart'))
    );
  }

  addPart(part: Part): Observable<Part> {
    return this.http.post<Part>(this.partsUrl, part, this.httpOptions).pipe(
      tap((newPart: Part) => this.log(`added part w/ id=${newPart.id}`)),
      catchError(this.handleError<Part>('addPart'))
    );
  }

  deletePart(id: number): Observable<Part> {
    const url = `${this.partsUrl}/${id}`;

    return this.http.delete<Part>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted part id=${id}`)),
      catchError(this.handleError<Part>('deletePart'))
    );
  }

}
