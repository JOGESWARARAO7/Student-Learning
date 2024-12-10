import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { NavbarComponent } from '../navbar/navbar.component';
import { getallproblem, Solveuser } from '../../AllClass/solveuser/solveuser';
import { error } from 'console';
import { SolveuserService } from '../../AllService/solveuser/solveuser.service';
import { CommonModule } from '@angular/common';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-homepage',
  standalone: true,
  imports: [RouterModule,FormsModule,NavbarComponent,CommonModule],
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.css'
})
export class HomepageComponent implements OnInit{
  [x: string]: any;
  branchSelection:string="";
  pdfs:getallproblem[]=[];
  questionid:any;

  constructor(private solveuser:SolveuserService,private sanitizer: DomSanitizer){
  }

  getallproblem(){

    this.solveuser.getAllPdfs(this.branchSelection).subscribe(data=>{
      this.pdfs = data;
    } ,error => {
      console.error("Error retrieving uploaded problems", error);
    })

  }

  getSafeUrl(filequestion: string | null): SafeResourceUrl | null {
    if (!filequestion) {
        return null;
    }
    return this.sanitizer.bypassSecurityTrustResourceUrl('data:application/pdf;base64,' + filequestion);
}

  
getquestionid(questionid: any){
  
  alert(questionid)
}
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

}
