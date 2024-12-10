import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-pro-navbar',
  standalone: true,
  imports: [RouterModule,FormsModule,CommonModule],
  templateUrl: './pro-navbar.component.html',
  styleUrl: './pro-navbar.component.css'
})
export class ProNavbarComponent implements OnInit{

  username: string | null = "";

  ngOnInit(): void {
    const token = sessionStorage.getItem('auth_token');
    if (token) {
        const parsedToken = this.decodeToken(token);
        this.username = parsedToken.username || parsedToken.email || '';  // Use username or email
    }
}

  private decodeToken(token: string): any {
    const payload = token.split('.')[1];
    const decodedPayload = JSON.parse(atob(payload)); // Decode JWT payload

    // Extract the username or email (from custom claim or 'sub' field)
    return {
        username: decodedPayload.username || decodedPayload.sub,  // Prefer username if present
        email: decodedPayload.sub  // Always have email as a fallback
    };
}

constructor(private router:Router){

}

Logout(){
  sessionStorage.removeItem('auth_token');
  this.router.navigate(["/problemlogin"]);
}
  

}




