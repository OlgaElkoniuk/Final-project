import { Component, OnInit } from '@angular/core';
import { Purchase } from 'src/app/shared/models/purchase';
import { PurchaseService } from 'src/app/shared/servises/purchase.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-purchase-coupon',
  templateUrl: './purchase-coupon.component.html',
  styleUrls: ['./purchase-coupon.component.css']
})
export class PurchaseCouponComponent implements OnInit {
  public purchase = new Purchase(parseInt(sessionStorage.getItem('id')),parseInt(sessionStorage.getItem('couponId')));
  
  constructor(private purchaseService: PurchaseService, private router: Router) { }

  public purchaseCoupon():void {

    this.purchaseService.addPurchase(this.purchase).subscribe(objectFromServer => {
      alert(
        "purchase was added"
        );
      }, err => {
          alert("Error! Status: " + err.status + ", Message: " + err.message);
      }); 
      
    }

  ngOnInit() {
  }

}
