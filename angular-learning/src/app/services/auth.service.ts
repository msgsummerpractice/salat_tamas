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
  private baseUrl =
    'https://salatt-backend-container-app.purpledesert-6358404e.germanywestcentral.azurecontainerapps.io';

  token = signal<string | null>(localStorage.getItem('token'));
  roles = signal<string | null>(localStorage.getItem('roles'));

  isLoggedIn = computed(() => !!this.token());
  isAdmin = computed(() => this.roles()?.includes('ADMIN') ?? false);

  login(request: SignInRequest) {
    return this.http.post<SignInResponse>(`${this.baseUrl}/api/auth/login`, request);
  }

  setSession(res: SignInResponse) {
    localStorage.setItem('token', res.token);
    const roleNames = Array.from(res.roles)
      .map((role) => role.name)
      .join(',');
    localStorage.setItem('roles', roleNames);
    this.token.set(res.token);
    this.roles.set(roleNames);
  }

  register(request: UserRequest) {
    return this.http.post<UserResponse>(`${this.baseUrl}/api/auth/register`, request);
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    this.token.set(null);
    this.roles.set(null);
    this.router.navigate(['/login']);
  }
}
