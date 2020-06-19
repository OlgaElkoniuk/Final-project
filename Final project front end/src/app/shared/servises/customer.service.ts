import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from '../models/customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  public constructor(private http: HttpClient) { }
  public addCustomer(customer:Customer): Observable <any>{
    return this.http.post("http://localhost:8080/customer", customer);
}
public update(customer:Customer): Observable <any>{
  return this.http.put("http://localhost:8080/customer?token="+sessionStorage.getItem('token'), customer);
}
public delete(customerId:number): Observable <any>{
  return this.http.delete("http://localhost:8080/customer/"+customerId+"?token="+sessionStorage.getItem('token'));
}
public getAll(): Observable <any>{
  return this.http.get("http://localhost:8080/customer?token="+sessionStorage.getItem('token'));
}
public getCustomer(): Observable <any>{
  return this.http.get("http://localhost:8080/customer/"+sessionStorage.getItem('id')+"?token="+sessionStorage.getItem('token'));
}
}
