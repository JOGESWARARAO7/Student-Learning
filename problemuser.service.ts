import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { problemuserprofile, Probleuser } from '../../AllClass/problemuser/probleuser';
import { Otp } from '../../AllClass/otp/otp';
import { Puserproblemupload } from '../../AllClass/puserproblemupload/puserproblemupload';
import { Observable } from 'rxjs';
import { SolveuserDatalog } from '../../AllClass/solveuser/solveuser';
import { Allproblems } from '../../AllClass/alluploadproblem/allproblems';

@Injectable({
  providedIn: 'root'
})
export class ProblemuserService {

  private probemuserURL="http://localhost:8080/problem";

  
 
  constructor(private http:HttpClient) { }

  // solve user register
  problemusersignup(problemuserData:Probleuser){
    
      return this.http.post(`${this.probemuserURL}/problemsignup`,problemuserData)
  }

  otpChecker(otpcheck:Otp){
    return this.http.post(`${this.probemuserURL}/otpChecked`,otpcheck)
  }

  loginCheck(email: String, password: String) {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const body = { email, password };
    return this.http.post<any>(`${this.probemuserURL}/login`, body, { headers });
}

submitQuestion(pupupload: Puserproblemupload): Observable<any> {
  const formData = new FormData();
  formData.append('branch', pupupload.branch);
  formData.append('question', pupupload.question);
  formData.append('userId', pupupload.userid.toString());
  
  if (pupupload.filequestion) {
    formData.append('fileQuestion', pupupload.filequestion, pupupload.filequestion.name);
  }
  if (pupupload.fileanswer) {
    formData.append('fileAnswer', pupupload.fileanswer, pupupload.fileanswer.name);
  }
  const headers = new HttpHeaders().set('Accept', 'application/json');
  return this.http.post(`${this.probemuserURL}/pupupload`, formData, { headers });
}


getAllQuestion(branchSelection: string, userid: number): Observable<Allproblems[]> {
  const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  const params = new HttpParams()
    .set('branchSelection', branchSelection)
    .set('userid', userid.toString()); // Change 'userId' to 'userid'

  return this.http.get<Allproblems[]>(`${this.probemuserURL}/getuploadproblem`, { headers, params });
}


uploadprofile(profileupload: problemuserprofile) {
  return this.http.post<problemuserprofile>(`${this.probemuserURL}/uploadprofile`, profileupload);
}

getinbultpuserprofile(userId:number){
  const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  const params = new HttpParams()
    .set('userid', userId.toString());
  return this.http.get<problemuserprofile>(`${this.probemuserURL}/getinbultpuserprofile`, { headers, params });
}

getmechanical(userId:number){
  const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  const params = new HttpParams().set('branch', 'mechanical')
  .set('userid', userId.toString());
  return this.http.get<number>(`${this.probemuserURL}/getmech`, { headers, params });
}

getcivil(userId:number) {
  const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  const params = new HttpParams().set('branch', 'civil').set('userid', userId.toString());
  return this.http.get<number>(`${this.probemuserURL}/getcivil`, { headers, params });
}

geteee(userId:number) {
  const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  const params = new HttpParams().set('branch', 'eee').set('userid', userId.toString());
  return this.http.get<number>(`${this.probemuserURL}/geteee`, { headers, params });
}

getcse(userId:number) {
  const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  const params = new HttpParams().set('branch', 'cse').set('userid', userId.toString());
  return this.http.get<number>(`${this.probemuserURL}/getcse`, { headers, params });
}


 }

