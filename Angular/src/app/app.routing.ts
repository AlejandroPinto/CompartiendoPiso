import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './register/register.component';

const appRoutes = [
    { path: 'register', component: RegisterComponent },
    { path: '', redirectTo: '', pathMatch: 'full' }
]
export const  routing = RouterModule.forRoot(appRoutes);