import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ProNavbarComponent } from '../pro-navbar/pro-navbar.component';
import { ProblemuserService } from '../../AllService/problemuser/problemuser.service';
import { subscribe } from 'diagnostics_channel';
import { error } from 'console';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-problem-dashboard',
  standalone: true,
  imports: [RouterModule,FormsModule,CommonModule,ProNavbarComponent],
  templateUrl: './problem-dashboard.component.html',
  styleUrl: './problem-dashboard.component.css'
})
export class ProblemDashboardComponent implements OnInit{

  userId:number=0;

   mechanical:number=0;
   civil:number=0;
   eee:number=0;
   cse:number=0;

  constructor(private http:ProblemuserService ){

  }

  ngOnInit(): void {

    const token = sessionStorage.getItem('auth_token');
    if (token) {
      const parsedToken = this.decodeToken(token);
      this.userId = parsedToken.userId;
      
     
    }


    forkJoin({
      mechanical: this.http.getmechanical(this.userId),
      civil: this.http.getcivil(this.userId),
      eee: this.http.geteee(this.userId),
      cse: this.http.getcse(this.userId)
    }).subscribe({
      next: (data) => {
        this.mechanical = data.mechanical;
        this.civil = data.civil;
        this.eee = data.eee;
        this.cse = data.cse;
      },
      error: (err) => {
        console.error(err);
        this.mechanical = this.civil = this.eee = this.cse = 0;
      }
    });



  }

    

    private decodeToken(token: string): any {
      try {
        const payload = token.split('.')[1];
        const decodedPayload = JSON.parse(atob(payload));
        return {
          userId: decodedPayload.userId,
          username: decodedPayload.username || decodedPayload.sub,
          email: decodedPayload.sub
        };
      } catch (error) {
        console.error("Failed to decode token", error);
        return {};
      }
    }


  }
  


