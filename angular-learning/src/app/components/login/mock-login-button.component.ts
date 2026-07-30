import { Component, inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-mock-login-button',
  templateUrl: './mock-login-button.component.html',
  imports: [MatButtonModule],
})
export class MockLoginButtonComponent {
  private authService = inject(AuthService);
  private router = inject(Router);

  mockLogin() {
    this.authService.login();
    this.router.navigate(['/home']);
  }
}
