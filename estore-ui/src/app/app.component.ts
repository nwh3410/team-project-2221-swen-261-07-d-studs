import { Component, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';
import { LoginComponent } from './login/login.component';



@Component({
  providers: [LoginComponent],
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  isAdmin: boolean
  title = 'Studly Computer Parts'
  constructor(private loginService: LoginService, private loginComponent: LoginComponent) {
    //log: LoginComponent
    this.isAdmin = this.loginService.isAdmin
    console.log("The front end has recieved adminstatus")
  }



  ngAfterContentChecked() {
    this.isAdmin = this.loginService.isAdmin
  }

  get getAdminStatus() {
    return this.loginService.isAdmin
  }


}
function username(username: any): any {
  throw new Error('Function not implemented.');
}

