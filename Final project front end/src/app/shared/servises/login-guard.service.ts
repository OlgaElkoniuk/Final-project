import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LoginGuardService implements CanActivate {

  constructor(
      private router: Router,
      private http: HttpClient
  ) { }

  canActivate(): Observable<any> {
      return this.http.get("http://localhost:8080/check?"+sessionStorage.getItem('token')).pipe(map(auth => {
          if (!auth) {
              this.router.navigate(['/login']);
              return false;
          } else {
              return true;
          }
      }, err => {
        this.router.navigate(['/login']);
              return false;
    }));
  }
}