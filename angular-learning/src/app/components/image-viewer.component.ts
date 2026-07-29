import { Component, OnDestroy, signal } from '@angular/core';
import { ImageService } from '../services/image.service';
import { Subscription, finalize } from 'rxjs';
import { MatButtonModule } from '@angular/material/button';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@Component({
  selector: 'app-image-viewer',
  standalone: true,
  imports: [MatButtonModule, MatProgressSpinnerModule],
  template: `
    <div class="flex flex-col items-center justify-center gap-4 min-w-40">
      <button
        mat-raised-button
        (click)="loadImage()"
        [disabled]="loading()"
        class="flex items-center gap-2"
      >
        @if (loading()) {
          <mat-progress-spinner
            mode="indeterminate"
            diameter="18"
            strokeWidth="3"
            class="w-full"
          ></mat-progress-spinner>
        } @else {
          <span class="w-full">Load Image</span>
        }
      </button>

      <div class="flex flex-col items-center justify-center gap-4">
        @if (imageUrl() && !loading()) {
          <div>
            <img [src]="imageUrl()" alt="Random" dog image />
          </div>
        }

        @if (error()) {
          <div>{{ error() }}</div>
        }
      </div>
    </div>
  `,
  styles: [
    `
      img {
        max-width: 100%;
        height: auto;
        max-height: 300px;
        border-radius: 8px;
      }

      button {
        display: inline-flex;
        align-items: center;
        gap: 8px;
      }
    `,
  ],
})
export class ImageViewerComponent implements OnDestroy {
  imageUrl = signal<string | null>(null);
  private sub?: Subscription;
  loading = signal(false);
  error = signal<string | null>(null);

  constructor(private imageService: ImageService) {}

  loadImage() {
    this.sub?.unsubscribe();
    this.loading.set(true);
    this.error.set(null);
    this.sub = this.imageService
      .fetchRandomDogImage()
      .pipe(finalize(() => this.loading.set(false)))
      .subscribe({
        next: (url) => {
          this.imageUrl.set(url);
        },
        error: (err) => {
          this.error.set(err.message || 'An error occurred while fetching the image.');
        },
      });
  }

  ngOnDestroy() {
    this.sub?.unsubscribe();
  }
}
