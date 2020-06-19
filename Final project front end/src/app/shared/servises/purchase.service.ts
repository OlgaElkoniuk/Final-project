import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Purchase } from '../models/purchase';

@Injectable({
  providedIn: 'root'
})
export class PurchaseService {

  public constructor(private http: HttpClient) { }

  public addPurchase(purchase:Purchase): Observable <any>{
      return this.http.post("http://localhost:8080/purchase?token="+sessionStorage.getItem('token'), purchase);
  }

public getAllPurchases(): Observable <any>{
  return this.http.get("http://localhost:8080/purchase?token="+sessionStorage.getItem('token'));
}

}
