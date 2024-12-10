import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';

import { CommonModule } from '@angular/common';
import { Probleuser } from '../../AllClass/problemuser/probleuser';
import { ProblemuserService } from '../../AllService/problemuser/problemuser.service';

@Component({
  selector: 'app-problemsign',
  standalone: true,
  imports: [RouterModule,FormsModule,CommonModule],
  templateUrl: './problemsign.component.html',
  styleUrl: './problemsign.component.css'
})
export class ProblemsignComponent implements OnInit{

  problemuserdata:Probleuser=new Probleuser();
  message:string="";
  ngOnInit(): void {
    
  }
  constructor(private problemservice:ProblemuserService,private router: Router){

  }
  ProblemuserData( ){
    this.problemservice.problemusersignup(this.problemuserdata).subscribe(data=>{
      this.router.navigate(['/problemotp']);
    },error=>
      this.message = error.error // Display the error message on the page
     
    );
  }

}
