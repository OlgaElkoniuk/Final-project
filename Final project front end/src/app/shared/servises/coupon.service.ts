import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Coupon } from '../models/coupon';

import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class CouponService {

  public constructor(private http: HttpClient) { }
  public addCoupon(coupon:Coupon): Observable <any>{
    return this.http.post("http://localhost:8080/coupons?token="+sessionStorage.getItem('token'), coupon);
}
public update(coupon:Coupon): Observable <any>{
  return this.http.post("http://localhost:8080/coupons?token="+sessionStorage.getItem('token'), coupon);
}
public delete(couponId:number): Observable <any>{
  return this.http.delete("http://localhost:8080/coupons/"+couponId+"?token="+sessionStorage.getItem('token'));
}
public getAll(): Observable <any>{
  return this.http.get("http://localhost:8080/coupons?token="+sessionStorage.getItem('token'));
}
public getCoupon(couponId:number): Observable <any>{
  return this.http.get("http://localhost:8080/coupons/"+couponId+"?token="+sessionStorage.getItem('token'));
}
}
