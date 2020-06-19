import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/shared/models/user';
import { UserService } from 'src/app/shared/servises/user.service';
import { Router } from '@angular/router';
import { Customer } from 'src/app/shared/models/customer';
import { CustomerService } from 'src/app/shared/servises/customer.service';

@Component({
  selector: 'app-company-user-profile',
  templateUrl: './company-user-profile.component.html',
  styleUrls: ['./company-user-profile.component.css']
})
export class CompanyUserProfileComponent implements OnInit {

  public user = new User();
  constructor(private userService: UserService, private router: Router) { }
  public getUserDetails():User {

    this.userService.getUserProfile().subscribe(objectFromServer => {
      this.user=objectFromServer;
      
      }, err => {
          alert("Error! Status: " + err.status + ", Message: " + err.message);
      }); 
      return this.user
    }
   
  ngOnInit() {
    this.getUserDetails()
  }

}
