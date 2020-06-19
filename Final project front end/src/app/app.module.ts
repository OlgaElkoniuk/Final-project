import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { AdminComponent } from './admin/admin.component';
import { CompanyComponent } from './company/company.component';
import { CustomerComponent } from './customer/customer.component';
import { HeaderComponent } from './header/header.component';
import { AddCompanyComponent } from './admin/add-company/add-company.component';
import { GetAllCompaniesComponent } from './admin/get-all-companies/get-all-companies.component';
import { GetCompanyByIdComponent } from './admin/get-company-by-id/get-company-by-id.component';
import { CreateCustomerComponent } from './customer/create-customer/create-customer.component';
import { GetAllCouponsComponent } from './customer/get-all-coupons/get-all-coupons.component';
import { GetAllUsersComponent } from './admin/get-all-users/get-all-users.component';
import { AddCouponComponent } from './company/add-coupon/add-coupon.component';
import { AddCompanyUserComponent } from './admin/add-company-user/add-company-user.component';
import { RegisterComponent } from './register/register.component';
import { DeleteCouponComponent } from './company/delete-coupon/delete-coupon.component';
import { AllCouponsComponent } from './company/all-coupons/all-coupons.component';
import { ProfileComponent } from './customer/profile/profile.component';
import { PurchaseCouponComponent } from './customer/purchase-coupon/purchase-coupon.component';
import { UpdateProfileComponent } from './customer/update-profile/update-profile.component';
import { CompanyUserProfileComponent } from './company/company-user-profile/company-user-profile.component';
import { UpdateCompanyUserProfileComponent } from './company/update-company-user-profile/update-company-user-profile.component';
import { RegisterCompanyUserComponent } from './company/register-company-user/register-company-user.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AdminComponent,
    CompanyComponent,
    CustomerComponent,
    HeaderComponent,
    AddCompanyComponent,
    GetAllCompaniesComponent,
    GetCompanyByIdComponent,
    CreateCustomerComponent,
    GetAllCouponsComponent,
    GetAllUsersComponent,
    AddCouponComponent,
    AddCompanyUserComponent,
    RegisterComponent,
    DeleteCouponComponent,
    AllCouponsComponent,
    ProfileComponent,
    PurchaseCouponComponent,
    UpdateProfileComponent,
    CompanyUserProfileComponent,
    UpdateCompanyUserProfileComponent,
    RegisterCompanyUserComponent,
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
