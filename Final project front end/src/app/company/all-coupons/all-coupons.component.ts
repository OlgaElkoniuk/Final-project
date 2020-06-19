import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/shared/models/coupon';
import { CouponService } from 'src/app/shared/servises/coupon.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-all-coupons',
  templateUrl: './all-coupons.component.html',
  styleUrls: ['./all-coupons.component.css']
})
export class AllCouponsComponent implements OnInit {

  public coupon = new Coupon();
  public coupons:Coupon[];
  
  constructor(private couponService: CouponService, private router: Router) { }
  public getAllCoupons():Coupon[] {

    this.couponService.getAll().subscribe(objectFromServer => {
      this.coupons = objectFromServer;
  
      }, err => {
          alert("Error! Status: " + err.status + ", Message: " + err.message);
      }); 
    return this.coupons
  }
  public deleteCoupon(coupon):void {

    this.couponService.delete(coupon.id).subscribe(objectFromServer => {
      alert(
        "coupon was deleted"
        );
        this.getAllCoupons()
      }, err => {
          alert("Error! Status: " + err.status + ", Message: " + err.message);
      }); 
      
    }
  ngOnInit() {
    this.getAllCoupons()
  }

}
