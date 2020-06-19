import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/shared/models/user';
import { UserService } from 'src/app/shared/servises/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-get-all-users',
  templateUrl: './get-all-users.component.html',
  styleUrls: ['./get-all-users.component.css']
})
export class GetAllUsersComponent implements OnInit {

  public user = new User();
  public users:User[];
  
  constructor(private userService: UserService, private router: Router) { }
  public getAllUsers():User[] {

    this.userService.getAllUsers().subscribe(objectFromServer => {
      this.users = objectFromServer;
  
      }, err => {
          alert("Error! Status: " + err.status + ", Message: " + err.message);
      }); 
    return this.users
  }

  ngOnInit() {
    this.getAllUsers()
  }

}
