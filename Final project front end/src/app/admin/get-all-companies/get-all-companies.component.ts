import { Component, OnInit } from '@angular/core';
import { Company } from 'src/app/shared/models/company';
import { CompanyService } from 'src/app/shared/servises/company.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-get-all-companies',
  templateUrl: './get-all-companies.component.html',
  styleUrls: ['./get-all-companies.component.css']
})
export class GetAllCompaniesComponent implements OnInit {

  public company = new Company();
  public companies:Company[];
  
  constructor(private companyService: CompanyService, private router: Router) { }
  public getAllCompanies():Company[] {

    this.companyService.getAll().subscribe(objectFromServer => {
      this.companies = objectFromServer;
  
      }, err => {
          alert("Error! Status: " + err.status + ", Message: " + err.message);
      }); 
    return this.companies
  }
  public deleteCompany(company):void {

    this.companyService.delete(company.id).subscribe(objectFromServer => {
      alert(
        "company was deleted"
        );
        this.getAllCompanies()
      }, err => {
          alert("Error! Status: " + err.status + ", Message: " + err.message);
      }); 
      
    }
  ngOnInit() {
    this.getAllCompanies()
  }

}
