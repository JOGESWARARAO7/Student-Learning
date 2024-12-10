import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { Router, RouterModule } from '@angular/router';


import { CommonModule } from '@angular/common';
import { ProNavbarComponent } from '../pro-navbar/pro-navbar.component';
import { Puserproblemupload } from '../../AllClass/puserproblemupload/puserproblemupload';
import { ProblemuserService } from '../../AllService/problemuser/problemuser.service';

@Component({
  selector: 'app-problemhome',
  standalone: true,
  imports: [FormsModule,RouterModule,ProNavbarComponent,CommonModule],
  templateUrl: './problemhome.component.html',
  styleUrl: './problemhome.component.css'
})
export class ProblemhomeComponent implements OnInit{
  message: string = "";
  isSuccess: boolean = true;
  pupupload: Puserproblemupload = new Puserproblemupload("", 0, "", null, null);

  constructor(private pusevice: ProblemuserService) {}
  

  onFileChange(event: any, field: 'filequestion' | 'fileanswer') {
    const file = event.target.files[0];
    if (file) {
      // Save file data in pupupload, which is an instance of Puserproblemupload
      this.pupupload[field] = file;
    }
  }
  

  onSubmit() {
    if (!this.pupupload.branch || !this.pupupload.question || !this.pupupload.filequestion || !this.pupupload.fileanswer) {
      alert("Please fill all fields");
      return;
    }
    this.pusevice.submitQuestion(this.pupupload).subscribe({
      next: (response) => {
        this.message = 'Question submitted successfully!';
        this.isSuccess = true; // Set to true for success
      },
      error: (error) => {
        this.message = error.error;
        this.isSuccess = false;
      }
    });
  }
  
  userid: number | undefined; 

  ngOnInit(): void {
    const token = sessionStorage.getItem('auth_token');
    if (token) {
      const parsedToken = this.decodeToken(token);
      this.pupupload.userid = parsedToken.userId;  // Correct retrieval of `userId`
      
    }
  }
  
  
  private decodeToken(token: string): any {
    try {
      const payload = token.split('.')[1];
      const decodedPayload = JSON.parse(atob(payload));
      return {
        userId: decodedPayload.userId,  // Retrieve `userId` directly
        username: decodedPayload.username || decodedPayload.sub,
        email: decodedPayload.sub
      };
    } catch (error) {
      console.error("Failed to decode token", error);
      return {};
    }
  }
  
  

}


