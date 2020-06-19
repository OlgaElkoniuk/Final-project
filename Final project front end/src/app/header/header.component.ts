import { Component, OnInit } from '@angular/core';
import { RouterStateSnapshot, Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  
  constructor(private router: Router) { }
  userName:string;
  public logout(){
    sessionStorage.clear();
  }
  ngOnInit() {
    this.userName = sessionStorage.getItem("userName");
  }

}
