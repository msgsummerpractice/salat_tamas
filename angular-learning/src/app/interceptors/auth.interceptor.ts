import { HttpInterceptorFn } from '@angular/common/http';
import { environments } from '../../environments/environments';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const cloned = req.clone({
    setHeaders: {
      Authorization: `Bearer ${environments.mockAuthToken}`,
    },
  });

  return next(cloned);
};
