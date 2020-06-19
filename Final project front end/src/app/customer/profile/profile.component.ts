import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/shared/models/user';
import { UserService } from 'src/app/shared/servises/user.service';
import { Router } from '@angular/router';
import { Customer } from 'src/app/shared/models/customer';
import { CustomerService } from 'src/app/shared/servises/customer.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  public user = new User();
  public customer = new Customer();
  constructor(private customerService:CustomerService, private userService: UserService, private router: Router) { }
  public getUserDetails():User {

    this.userService.getUserProfile().subscribe(objectFromServer => {
      this.user=objectFromServer;
      
      }, err => {
          alert("Error! Status: " + err.status + ", Message: " + err.message);
      }); 
      return this.user
    }
    public getCustomerDetails():Customer {

      this.customerService.getCustomer().subscribe(objectFromServer => {
        this.customer=objectFromServer;
        
        }, err => {
            alert("Error! Status: " + err.status + ", Message: " + err.message);
        }); 
        return this.customer
      }
  ngOnInit() {
    this.getCustomerDetails()
    this.getUserDetails()
  }

}
