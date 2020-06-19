import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/shared/models/user';
import { UserService } from 'src/app/shared/servises/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register-company-user',
  templateUrl: './register-company-user.component.html',
  styleUrls: ['./register-company-user.component.css']
})
export class RegisterCompanyUserComponent implements OnInit {
  public user = new User();
  constructor(private userService: UserService, private router: Router) { }
  public registerCompanyUser():void {
this.user.companyId = parseInt(sessionStorage.getItem("companyId"));
    this.userService.register(this.user).subscribe(objectFromServer => {
      alert(
        "company user was registered"
        );
      }, err => {
          alert("Error! Status: " + err.status + ", Message: " + err.message);
      }); 
    
  }
  ngOnInit() {
  }
}
