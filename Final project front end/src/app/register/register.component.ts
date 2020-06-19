import { Component, OnInit } from '@angular/core';
import { User } from '../shared/models/user';
import { UserService } from '../shared/servises/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  public user = new User();
  constructor(private userService: UserService, private router: Router) { }
  public registerUser():void {

    this.userService.register(this.user).subscribe(objectFromServer => {
      alert(
        "user was registered"
        );
      }, err => {
          alert("Error! Status: " + err.status + ", Message: " + err.message);
      }); 
    
  }
  ngOnInit() {
  }

}
