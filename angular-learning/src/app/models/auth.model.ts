export interface SignInRequest {
  email: string;
  password: string;
}

export interface UserRequest {
  email: string;
  password: string;
  username: string;
  firstname: string;
  lastname: string;
}

export interface Role {
  name: string;
}

export interface SignInResponse {
  token: string;
  role: Role;
}

export interface UserResponse {
  id: number;
  email: string;
  username: string;
  firstname: string;
  lastname: string;
  role: Role;
  createdAt: string;
}
