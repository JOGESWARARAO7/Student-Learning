import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { routes } from '../../app.routes';
import { RouterModule } from '@angular/router';
import { ProNavbarComponent } from '../pro-navbar/pro-navbar.component';
import { problemuserprofile } from '../../AllClass/problemuser/probleuser';
import { ProblemuserService } from '../../AllService/problemuser/problemuser.service';
import { error } from 'node:console';

@Component({
  selector: 'app-myprofile',
  standalone: true,
  imports: [FormsModule,CommonModule,RouterModule,ProNavbarComponent],
  templateUrl: './myprofile.component.html',
  styleUrl: './myprofile.component.css'
})
export class MyprofileComponent {

  pmuserprofile:problemuserprofile=new problemuserprofile();
  pmuserprofile1:problemuserprofile=new problemuserprofile();
  userId:number=0;
  username:string="";
 
  profileUpdated: boolean = false;
  

  constructor(private problemService: ProblemuserService) {}

  ngOnInit(): void {
    

    const token = sessionStorage.getItem('auth_token');
    if (token) {
      const parsedToken = this.decodeToken(token);
      this.userId = parsedToken.userId;
      this.username=parsedToken.username;
      
     
    }

    // auto get profile
    this.problemService.getinbultpuserprofile(this.userId).subscribe(data=>{
      this.pmuserprofile1=data;
      this.profileUpdated = true;

    },error=>{

    })


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


 
  profileupdate() {
    
    if (this.username && this.userId) {
        // Populate necessary fields in pmuserprofile
        this.pmuserprofile.userid = this.userId;
        this.pmuserprofile.username = this.username;
       
       
        
        // Call the service method to upload the profile
        this.problemService.uploadprofile(this.pmuserprofile).subscribe(
            data => {
                alert("Profile uploaded successfully!");
                this.pmuserprofile1 = data;
                this.profileUpdated = true;

            },
            error => {
                console.error("Error during profile upload", error);
                alert("Failed to upload profile. Please try again.");
            }
        );
    } else {
        alert("User name and ID are not present.");
    }
}


  

}
