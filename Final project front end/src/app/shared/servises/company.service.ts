import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Company } from '../models/company';


@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  public constructor(private http: HttpClient) { }
  public addCompany(company:Company): Observable <any>{
    
    return this.http.post("http://localhost:8080/company?token="+sessionStorage.getItem('token'), company);
}
public update(company:Company): Observable <any>{
  return this.http.post("http://localhost:8080/company?token="+sessionStorage.getItem('token'), company);
}
public delete(companyId:number): Observable <any>{
  return this.http.delete("http://localhost:8080/company/"+companyId+"?token="+sessionStorage.getItem('token'));
}
public getAll(): Observable <any>{
  return this.http.get("http://localhost:8080/company?token="+sessionStorage.getItem('token'));
}
public getCompany(companyId:number): Observable <any>{
  return this.http.get("http://localhost:8080/company/"+companyId+"?token="+sessionStorage.getItem('token'));
}
}
