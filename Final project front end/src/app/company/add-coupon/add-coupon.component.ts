import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/shared/models/coupon';
import { CouponService } from 'src/app/shared/servises/coupon.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-coupon',
  templateUrl: './add-coupon.component.html',
  styleUrls: ['./add-coupon.component.css']
})
export class AddCouponComponent implements OnInit {
  public coupon = new Coupon();
  constructor(private couponService: CouponService, private router: Router) { }
  public addCoupon():void {

    this.couponService.addCoupon(this.coupon).subscribe(objectFromServer => {
      alert(
        "coupon was addedd"
        );
      }, err => {
          alert("Error! Status: " + err.status + ", Message: " + err.message);
      }); 
    
  }
  ngOnInit() {
  }

}
