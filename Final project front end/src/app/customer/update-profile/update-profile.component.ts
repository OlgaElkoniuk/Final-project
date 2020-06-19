import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/shared/models/user';
import { UserService } from 'src/app/shared/servises/user.service';
import { Router } from '@angular/router';
import { Customer } from 'src/app/shared/models/customer';
import { CustomerService } from 'src/app/shared/servises/customer.service';

@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.css']
})
export class UpdateProfileComponent implements OnInit {

  public user = new User();
  public customer = new Customer();

  
  constructor(private userService: UserService, private customerService:CustomerService,private router: Router) { }
  public updateUser():void {

    this.userService.update(this.user).subscribe(objectFromServer => {
      
  
      }, err => {
          alert("Error! Status: " + err.status + ", Message: " + err.message);
      }); 
      this.customerService.update(this.customer).subscribe(objectFromServer => {
        alert("profile was updated");
    
        }, err => {
            alert("Error! Status: " + err.status + ", Message: " + err.message);
        }); 
  }

  ngOnInit() {
  }

}
