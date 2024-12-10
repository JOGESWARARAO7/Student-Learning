import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';

import { CommonModule } from '@angular/common';
import { problemuserDatalog } from '../../AllClass/problemuser/probleuser';
import { ProblemuserService } from '../../AllService/problemuser/problemuser.service';

@Component({
  selector: 'app-problemlogin',
  standalone: true,
  imports: [RouterModule,FormsModule,CommonModule],
  templateUrl: './problemlogin.component.html',
  styleUrl: './problemlogin.component.css'
})
export class ProblemloginComponent implements OnInit{

  email:string="";
  password:string="";
  message:any="";
  problemuserdat:problemuserDatalog=new problemuserDatalog();
constructor(private problemUserServic:ProblemuserService,private router:Router){

}
login() {
  this.problemUserServic.loginCheck(this.email, this.password).subscribe(
    (data:any) => {
      sessionStorage.setItem('auth_token', data.token); // Store user data in local storage
          this.router.navigate(['/problemhome']);
      },
      error =>{
        this.message = error.error;
        sessionStorage.removeItem('auth_token');
       
      }

  );
}



  ngOnInit(): void {
    
  }



}
