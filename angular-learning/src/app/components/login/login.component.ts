import { Component } from '@angular/core';
import { MockLoginButtonComponent } from './mock-login-button.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  imports: [MockLoginButtonComponent],
})
export class LoginComponent {}
