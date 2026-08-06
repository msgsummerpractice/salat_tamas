import { HttpClient } from '@angular/common/http';
import { computed, inject, Injectable, signal } from '@angular/core';
import { Router } from '@angular/router';
import { SignInRequest, SignInResponse, UserRequest, UserResponse } from '../models/auth.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private http = inject(HttpClient);
  private router = inject(Router);

  token = signal<string | null>(localStorage.getItem('token'));
  role = signal<string | null>(localStorage.getItem('role'));

  isLoggedIn = computed(() => !!this.token());
  isAdmin = computed(() => this.role() === 'ADMIN');

  login(request: SignInRequest) {
    return this.http.post<SignInResponse>('/api/auth/login', request);
  }

  setSession(res: SignInResponse) {
    localStorage.setItem('token', res.token);
    localStorage.setItem('role', res.role.name);
    this.token.set(res.token);
    this.role.set(res.role.name);
  }

  register(request: UserRequest) {
    return this.http.post<UserResponse>('/api/auth/register', request);
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    this.token.set(null);
    this.role.set(null);
    this.router.navigate(['/home']);
  }
}
