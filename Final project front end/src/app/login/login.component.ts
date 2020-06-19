import { Component, OnInit } from '@angular/core';
import { UserDataClient } from "../shared/models/UserDataClient";
import { Credentials } from '../shared/models/credentials';
import { UserService } from '../shared/servises/user.service';
import { Router } from '@angular/router';




@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public credentials = new Credentials();
  public userDataClient = new UserDataClient();

  constructor(private userService: UserService, private router: Router) { }

  public login(): UserDataClient {

    this.userService.login(this.credentials).subscribe(objectFromServer => {
      if (objectFromServer.clientType === 'COMPANY'){
        sessionStorage.clear();
        sessionStorage.setItem("userName", objectFromServer.userName+"");
        sessionStorage.setItem("token", objectFromServer.token+"");
        sessionStorage.setItem("id", objectFromServer.id+"");
        sessionStorage.setItem("companyId", objectFromServer.companyId+"");
        this.router.navigate(["/company"]);
    }
    else if (objectFromServer.clientType === 'CUSTOMER'){
      sessionStorage.clear();
      sessionStorage.setItem("userName", objectFromServer.userName+"");
      sessionStorage.setItem("token", objectFromServer.token+"");
      sessionStorage.setItem("id", objectFromServer.id+"");
        this.router.navigate(["/customer"]);
    }
    else if (objectFromServer.clientType === 'ADMIN'){
      sessionStorage.clear();
      sessionStorage.setItem("userName", objectFromServer.userName+"");
      sessionStorage.setItem("token", objectFromServer.token+"");
      sessionStorage.setItem("id", objectFromServer.id+"");
        this.router.navigate(["/admin"]);
    }
    this.userDataClient=objectFromServer;
   
    });
    return this.userDataClient; 
  }
  ngOnInit() {
  }

}
