import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';

import { CommonModule } from '@angular/common';

import { error } from 'console';
import { SolveuserDatalog } from '../../AllClass/solveuser/solveuser';
import { SolveuserService } from '../../AllService/solveuser/solveuser.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule,RouterModule,CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{

  email:string="";
  password:string="";
  message:string="";
  solveuserdat:SolveuserDatalog=new SolveuserDatalog;
constructor(private solveUserServic:SolveuserService,private router:Router){

}
login() {
  this.solveUserServic.loginCheck(this.email, this.password).subscribe(
      (data:any) => {
        sessionStorage.setItem('auth_token', data.token);
          // localStorage.setItem('username', JSON.stringify(data)); // Store user data in local storage
          this.router.navigate(['/homepage']);
      },
      error =>{this.message = error.error
        // localStorage.removeItem('username')
        sessionStorage.removeItem('auth_token');
      }

  );
}



  ngOnInit(): void {
    
  }


}
