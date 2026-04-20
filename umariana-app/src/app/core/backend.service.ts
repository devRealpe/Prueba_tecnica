/**
 * Servicio Angular de ejemplo para consumir endpoints de Spring Boot.
 */

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserModel } from './models/UserModel';

export interface User extends UserModel {
  name: string;
  email: string;
}

@Injectable({
  providedIn: 'root'
})
export class BackendService {
  // Ruta base
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  listItems(): Observable<User[]> {
    return this.http.get<User[]>(`${this.baseUrl}/usuarios`);

  }

  getItem(id: number): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/usuarios/${id}`);
  }

  createItem(item: User): Observable<User> {
    return this.http.post<User>(`${this.baseUrl}/usuarios`, item);
  }

  updateItem(id: number, item: User): Observable<User> {
    return this.http.put<User>(`${this.baseUrl}/usuarios/${id}`, item);
  }

  deleteItem(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/usuarios/${id}`);
  }
}
