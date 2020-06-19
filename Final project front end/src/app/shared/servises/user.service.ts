import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserDataClient } from "../models/UserDataClient";
import { Credentials } from '../models/credentials';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  

  public constructor(private http: HttpClient) { }

  public login(credentials:Credentials): Observable <UserDataClient>{
      return this.http.post("http://localhost:8080/user/login", credentials);
  }
  public register(user:User): Observable <any>{
    return this.http.post("http://localhost:8080/user", user);
}
public update(user:User): Observable <any>{
  return this.http.put("http://localhost:8080/user?token="+sessionStorage.getItem('token'),user);
}
public delete(id:number): Observable <any>{
  return this.http.delete("http://localhost:8080/user/"+id+"?token="+sessionStorage.getItem('token'));
}
public getAllUsers(): Observable <any>{
  return this.http.get("http://localhost:8080/user?token="+sessionStorage.getItem('token'));
}
public getUserProfile(): Observable <any>{
  return this.http.get("http://localhost:8080/user/"+sessionStorage.getItem('id')+"?token="+sessionStorage.getItem('token'));
} 
public getUserById(id:number): Observable <any>{
  return this.http.get("http://localhost:8080/user/"+id+"?token="+sessionStorage.getItem('token'));
}
}
