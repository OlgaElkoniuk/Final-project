import { Component, OnInit } from '@angular/core';
import { CompanyService } from 'src/app/shared/servises/company.service';
import { Router } from '@angular/router';
import { Company } from 'src/app/shared/models/company';

@Component({
  selector: 'app-get-company-by-id',
  templateUrl: './get-company-by-id.component.html',
  styleUrls: ['./get-company-by-id.component.css']
})
export class GetCompanyByIdComponent implements OnInit {
  public company = new Company();
  public companyId:number;
  constructor(private companyService: CompanyService, private router: Router) { }
  public getCompanyById():Company {

    this.companyService.getCompany(this.companyId).subscribe(objectFromServer => {
      this.company=objectFromServer;
      
      }, err => {
          alert("Error! Status: " + err.status + ", Message: " + err.message);
      }); 
      return this.company
    }
  ngOnInit() {
  }

}
