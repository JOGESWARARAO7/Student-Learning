import { Routes } from '@angular/router';
import path from 'path';
import { EnterpageComponent } from './enterpage/enterpage.component';
import { LoginComponent } from './SolveUserAllComponent/login/login.component';
import { ProblemloginComponent } from './ProblemUserAllComponents/problemlogin/problemlogin.component';
import { SignupComponent } from './SolveUserAllComponent/signup/signup.component';
import { ProblemsignComponent } from './ProblemUserAllComponents/problemsign/problemsign.component';
import { OtpComponent } from './SolveUserAllComponent/otp/otp.component';
import { NavbarComponent } from './SolveUserAllComponent/navbar/navbar.component';
import { HomepageComponent } from './SolveUserAllComponent/homepage/homepage.component';
import { ProblemotpComponent } from './ProblemUserAllComponents/problemOTP/problemotp/problemotp.component';
import { ProblemhomeComponent } from './ProblemUserAllComponents/problemhome/problemhome.component';
import { PUserproblemComponent } from './ProblemUserAllComponents/p-userproblem/p-userproblem.component';
import { MyprofileComponent } from './ProblemUserAllComponents/myprofile/myprofile.component';
import { ProblemDashboardComponent } from './ProblemUserAllComponents/problem-dashboard/problem-dashboard.component';


export const routes: Routes = [
    {
        path: '',
        redirectTo:'enterpage',
        pathMatch:'full'  
    },
    {
        path:"enterpage",
        component:EnterpageComponent

    },
    {
        path:"login",
        component:LoginComponent
    },
    {
        path:"problemlogin",
        component:ProblemloginComponent

    },
    {
        path:"signup",
        component:SignupComponent
    },
    {
        path:"problemsignup",
        component:ProblemsignComponent
    },
    
    {
        path:"otp",
        component:OtpComponent
    },
    {
        path:"navbar",
        component:NavbarComponent
    },
    {
        path:"homepage",
        component:HomepageComponent
    },
    {
        path:"problemotp",
        component:ProblemotpComponent
    },
    {
        path:"problemhome",
        component:ProblemhomeComponent
    }
    ,
    {
        path:"puserproblem",
        component:PUserproblemComponent
    },
    {
        path:"myprofile",
        component:MyprofileComponent
    },
    {
        path:"problemdashboard",
        component:ProblemDashboardComponent
    }


];
