import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CompanyService } from 'src/app/shared/servises/company.service';
import { Company } from 'src/app/shared/models/company';
import { LoginGuardService } from 'src/app/shared/servises/login-guard.service';
@Component({
  selector: 'app-add-company',
  templateUrl: './add-company.component.html',
  styleUrls: ['./add-company.component.css']
})
export class AddCompanyComponent implements OnInit {
  public company = new Company();
  constructor(private companyService: CompanyService,private router: Router) { }
  public addCompany():void {

    this.companyService.addCompany(this.company).subscribe(objectFromServer => {
      alert(
        "company was addedd"
        );
      }, err => {
          alert("Error! Status: " + err.status + ", Message: " + err.message);
      }); 
    
  }
  ngOnInit() {
    
  }

}
