import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { IndexComponent } from './index/index.component';

const appRoutes = [
    { path: 'register', component: RegisterComponent },
    { path: 'index', component: IndexComponent },
    { path: '', redirectTo: 'index', pathMatch: 'full' }
]
export const  routing = RouterModule.forRoot(appRoutes);