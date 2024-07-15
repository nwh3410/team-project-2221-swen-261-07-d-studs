import { emitDistinctChangesOnlyDefaultValue } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AppComponent } from '../app.component';
import { Login } from '../login';
import { LoginService } from '../login.service';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public username = ''
  private password = ''
  public adminStatus : boolean
  public users : Map <String, String>

  constructor(private route: ActivatedRoute, private Loginservice: LoginService) {
    this.adminStatus = false
    this.users = new Map <String, String>
  }

  ngOnInit(): void {
  }

  setPassword(password: string) {
    this.password = password
  }

  setUsername(username: string) {
    this.username = username
  }

  getUsername() {
    return this.username
  }

  getPassword() {
    return this.password
  }

  onClickSubmit(data: any) {
    this.setUsername
    this.setPassword
    this.users.set(this.username, this.password)
    console.log(this.users)
    console.log(this.route.snapshot.paramMap.get('username'))
    this.Loginservice.getLogin(this.username, this.password).subscribe((isAdmin) => {
      this.adminStatus = isAdmin
     // this.Appcomponent.isAdmin = isAdmin
     // console.log(this.Appcomponent.isAdmin)
    })
    this.Loginservice.findLogin(this.username,this.password, "find").subscribe((userfound) => {
      console.log(userfound)
      
    }
    


    )

  
    console.log(data)
  }

  get getAdminStatus() {
    return this.Loginservice.isAdmin
  }


}
