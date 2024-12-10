import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-enterpage',
  standalone: true,
  imports: [RouterModule,FormsModule],
  templateUrl: './enterpage.component.html',
  styleUrl: './enterpage.component.css'
})
export class EnterpageComponent {

}
