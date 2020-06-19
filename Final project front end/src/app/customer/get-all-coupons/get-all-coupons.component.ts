import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/shared/models/coupon';
import { CouponService } from 'src/app/shared/servises/coupon.service';
import { Router } from '@angular/router';
import { Purchase } from 'src/app/shared/models/purchase';

@Component({
  selector: 'app-get-all-coupons',
  templateUrl: './get-all-coupons.component.html',
  styleUrls: ['./get-all-coupons.component.css']
})
export class GetAllCouponsComponent implements OnInit {

  public coupon = new Coupon();
  public coupons:Coupon[];
  public purchase = new Purchase(parseInt(sessionStorage.getItem('id')),this.coupon.id);
  
  constructor(private couponService: CouponService, private router: Router) { }
  public getAllCoupons():Coupon[] {

    this.couponService.getAll().subscribe(objectFromServer => {
      this.coupons = objectFromServer;
  
      }, err => {
          alert("Error! Status: " + err.status + ", Message: " + err.message);
      }); 
    return this.coupons
  }
public goToPurchase(coupon){
  sessionStorage.setItem("couponId", coupon.id+"");
  this.router.navigate(["/customer/purchase"]);
}
  ngOnInit() {
    this.getAllCoupons()
  }
}
