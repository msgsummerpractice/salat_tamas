import { Component, inject } from '@angular/core';
import {
  FormControl,
  NonNullableFormBuilder,
  Validators,
  ReactiveFormsModule,
} from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

type LoginForm = {
  email: FormControl<string>;
  password: FormControl<string>;
};

@Component({
  selector: 'app-login-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './login-form.component.html',
})
export class LoginFormComponent {
  private readonly _formBuilder = inject(NonNullableFormBuilder);
  private readonly authService = inject(AuthService);
  private readonly router = inject(Router);

  protected readonly loginFormGroup = this._formBuilder.group<LoginForm>({
    email: this._formBuilder.control('', [Validators.required, Validators.email]),
    password: this._formBuilder.control('', [
      Validators.required,
      Validators.minLength(8),
      Validators.pattern(/[A-Z]/),
      Validators.pattern(/[a-z]/),
      Validators.pattern(/[0-9]/),
    ]),
  });

  onSubmit(): void {
    if (this.loginFormGroup.invalid) return;

    const { email, password } = this.loginFormGroup.getRawValue();

    this.authService.login({ email, password }).subscribe({
      next: (res) => {
        this.authService.setSession(res);
        this.router.navigate(['/home']);
      },
      error: (err) => {
        console.error('Login failed:', err);
      },
    });
  }
}
