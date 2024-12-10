import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { getallproblem, Solveuser } from '../../AllClass/solveuser/solveuser';
import { Otp } from '../../AllClass/otp/otp';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SolveuserService {


  private solveuser="http://localhost:8080/solve";
  constructor(private http:HttpClient) { }

  // solve user register
  solveusersignup(solveuser:Solveuser){
    console.log(solveuser.email)
      return this.http.post(`${this.solveuser}/signup`,solveuser)
  }

  otpChecker(otpcheck:Otp){
    return this.http.post(`${this.solveuser}/otpChecked`,otpcheck)
  }

  loginCheck(email: String, password: String) {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const body = { email, password };
    return this.http.post<any>(`${this.solveuser}/login`, body, { headers });
}



getAllPdfs(branchSelection: string): Observable<getallproblem[]> {
  const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  const params = new HttpParams().set('branchSelection', branchSelection);
  return this.http.get<getallproblem[]>(`${this.solveuser}/getallpdfproblem`, { headers, params });
}


}
