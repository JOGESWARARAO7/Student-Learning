import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';

import { error } from 'console';
import { CommonModule } from '@angular/common';
import { Solveuser } from '../../AllClass/solveuser/solveuser';
import { SolveuserService } from '../../AllService/solveuser/solveuser.service';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [RouterModule,FormsModule,CommonModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent implements OnInit{

  solveuser:Solveuser=new Solveuser();

  message:string="";

  ngOnInit(): void {
    
  }
 
  constructor(private solveuserservice:SolveuserService,private router: Router){

  }

  
  
  SolveuserData(){
      this.solveuserservice.solveusersignup(this.solveuser).subscribe(data=>{
        this.router.navigate(['/otp']);
      },(error: { error: string; })=>
        this.message = error.error // Display the error message on the page
       
      );
  }


  
  

}
