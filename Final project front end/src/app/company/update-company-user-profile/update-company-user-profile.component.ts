import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/shared/models/user';
import { UserService } from 'src/app/shared/servises/user.service';
import { Router } from '@angular/router';
import { Customer } from 'src/app/shared/models/customer';
import { CustomerService } from 'src/app/shared/servises/customer.service';

@Component({
  selector: 'app-update-company-user-profile',
  templateUrl: './update-company-user-profile.component.html',
  styleUrls: ['./update-company-user-profile.component.css']
})
export class UpdateCompanyUserProfileComponent implements OnInit {

  public user = new User();

  
  constructor(private userService: UserService,private router: Router) { }
  public updateUser():void {

    this.userService.update(this.user).subscribe(objectFromServer => {
      alert("profile was updated");
  
      }, err => {
          alert("Error! Status: " + err.status + ", Message: " + err.message);
      }); 
  }

  ngOnInit() {
  }

}
