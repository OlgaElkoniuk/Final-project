import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { CompanyComponent } from './company/company.component';
import { CustomerComponent } from './customer/customer.component';
import { LoginComponent } from './login/login.component';
import { AddCompanyComponent } from './admin/add-company/add-company.component';
import { GetAllCompaniesComponent } from './admin/get-all-companies/get-all-companies.component';
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



const routes: Routes = [{ path: 'admin', component: AdminComponent }, 

{ path: '', component: LoginComponent },
{ path: 'register', component: RegisterComponent },
{ path: 'company', component: CompanyComponent },
{ path: 'admin/addCompany', component: AddCompanyComponent},
{ path: 'admin/getAllCompanies', component: GetAllCompaniesComponent},
{ path: 'admin/getAllUsers', component: GetAllUsersComponent},
{ path: 'addCompanyUser', component: AddCompanyUserComponent},
{ path: 'company/addCoupon', component: AddCouponComponent},
{ path: 'company/deleteCoupon', component: DeleteCouponComponent},
{ path: 'company/allCoupons', component: AllCouponsComponent},
{ path: 'company/addUser', component: RegisterCompanyUserComponent},
{ path: 'company/profile', component: CompanyUserProfileComponent},
{ path: 'company/updateProfile', component: UpdateCompanyUserProfileComponent},
{ path: 'customer', component: CustomerComponent },
{ path: 'customer/purchase', component: PurchaseCouponComponent},
{ path: 'customer/update', component: UpdateProfileComponent},
{ path: 'customer/profile', component: ProfileComponent}];

@NgModule({

  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
