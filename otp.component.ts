import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';

import { error } from 'console';
import { CommonModule } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Otp } from '../../AllClass/otp/otp';
import { SolveuserService } from '../../AllService/solveuser/solveuser.service';


@Component({
  selector: 'app-otp',
  standalone: true,
  imports: [RouterModule,FormsModule,CommonModule],
  templateUrl: './otp.component.html',
  styleUrl: './otp.component.css'
})
export class OtpComponent implements OnInit{

  otpcheck:Otp=new Otp();

  message:String="";

  constructor(private solveuser:SolveuserService,private router:Router){

  }
  ngOnInit(): void {
    
  }

  otpChecking() {
    this.solveuser.otpChecker(this.otpcheck).subscribe(
      data => {
        console.log("Success response:", data);
        this.router.navigate(['/login']);
      },
      (error: HttpErrorResponse) => {
        console.error("Error response:", error);
        if (error.error) {
          alert(error.error);
          this.router.navigate(['/signup']);
        } else {
          alert("An unexpected error occurred.");
          this.router.navigate(['/signup']);
        }
        if (error.status === 404) {
          this.router.navigate(['/signup']);
        } else if (error.status === 403) {
          alert("Access denied: Please check your permissions or contact support.");
        }
      }
    );
  }
  

}
