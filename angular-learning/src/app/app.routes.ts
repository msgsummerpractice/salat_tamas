import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full',
  },
  {
    path: 'home',
    loadComponent: () => import('./components/home.component').then((m) => m.HomeComponent),
    title: 'Home',
  },
  {
    path: 'login',
    loadComponent: () => import('./components/login.component').then((m) => m.LoginComponent),
    title: 'Login',
  },
  {
    path: '**',
    loadComponent: () =>
      import('./components/not-found.component').then((m) => m.NotFoundComponent),
    title: 'Page Not Found',
  },
];
