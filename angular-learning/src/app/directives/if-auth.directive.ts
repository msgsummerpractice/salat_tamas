import { Directive, effect, inject, input, TemplateRef, ViewContainerRef } from '@angular/core';

@Directive({ selector: `[appIfAuth]`, standalone: true })
export class IfAuthDirective {
  private readonly _viewContainerRef = inject(ViewContainerRef);
  private readonly _templateRef = inject(TemplateRef);

  appIfAuth = input<boolean>(false);

  constructor() {
    effect(() => {
      if (this.appIfAuth()) {
        this._viewContainerRef.createEmbeddedView(this._templateRef);
      } else {
        this._viewContainerRef.clear();
      }
    });
  }
}
