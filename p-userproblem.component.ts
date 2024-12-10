import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ProNavbarComponent } from '../pro-navbar/pro-navbar.component';
import { ProblemuserService } from '../../AllService/problemuser/problemuser.service';
import { Allproblems } from '../../AllClass/alluploadproblem/allproblems';

@Component({
  selector: 'app-p-userproblem',
  standalone: true,
  imports: [RouterModule, FormsModule, CommonModule, ProNavbarComponent],
  templateUrl: './p-userproblem.component.html',
  styleUrls: ['./p-userproblem.component.css']
})
export class PUserproblemComponent implements OnInit {
  branchSelection: string = "";
  userid: number | undefined; 
  alluploadproblems: Allproblems[] = [];

  constructor(private problemService: ProblemuserService) {}

  ngOnInit(): void {
    const token = sessionStorage.getItem('auth_token');
    if (token) {
      const parsedToken = this.decodeToken(token);
      this.userid = parsedToken.userId;
     
    }
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

  getUploadproblem() {
    if (this.userid) {
      
      this.problemService.getAllQuestion(this.branchSelection, this.userid)
        .subscribe(
          data => {
            this.alluploadproblems = data; // Assign the received data
          },
          error => {
            console.error("Error retrieving uploaded problems", error);
          }
        );
    } else {
      console.warn("User ID is undefined; cannot fetch problems");
    }
  }
}
