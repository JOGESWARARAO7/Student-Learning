import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';

import { HttpErrorResponse } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { Otp } from '../../../AllClass/otp/otp';
import { ProblemuserService } from '../../../AllService/problemuser/problemuser.service';

@Component({
  selector: 'app-problemotp',
  standalone: true,
  imports: [RouterModule,FormsModule,CommonModule],
  templateUrl: './problemotp.component.html',
  styleUrl: './problemotp.component.css'
})
export class ProblemotpComponent implements OnInit {

  otpcheck:Otp=new Otp();

  message:String="";

  constructor(private problemgiver:ProblemuserService,private router:Router){

  }
  ngOnInit(): void {
    
  }

  otpChecking() {
    this.problemgiver.otpChecker(this.otpcheck).subscribe(
      data => {
        console.log("Success response:", data);
        this.router.navigate(['/problemlogin']);
      },
      (error: HttpErrorResponse) => {
        console.error("Error response:", error);
        if (error.error) {
          alert(error.error);
          this.router.navigate(['/problemsignup']);
        } else {
          alert("An unexpected error occurred.");
          this.router.navigate(['/problemsignup']);
        }
        if (error.status === 404) {
          this.router.navigate(['/problemsignup']);
        } else if (error.status === 403) {
          alert("Access denied: Please check your permissions or contact support.");
        }
      }
    );
  }
  


}
