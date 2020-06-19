import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/shared/models/user';
import { UserService } from 'src/app/shared/servises/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-company-user',
  templateUrl: './add-company-user.component.html',
  styleUrls: ['./add-company-user.component.css']
})
export class AddCompanyUserComponent implements OnInit {
  public user = new User();
  constructor(private userService: UserService, private router: Router) { }
  public registerCompanyUser():void {

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
