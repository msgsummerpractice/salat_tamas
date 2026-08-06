import { Component, inject, OnDestroy, signal } from '@angular/core';
import { ImageService } from '../../services/image.service';
import { Subscription, finalize } from 'rxjs';
import { MatButtonModule } from '@angular/material/button';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@Component({
  selector: 'app-image-viewer',
  standalone: true,
  imports: [MatButtonModule, MatProgressSpinnerModule],
  templateUrl: './image-viewer.component.html',
  styleUrls: ['./image-viewer.component.css'],
})
export class ImageViewerComponent implements OnDestroy {
  imageUrl = signal<string | null>(null);
  private sub?: Subscription;
  loading = signal(false);
  error = signal<string | null>(null);
  imageService = inject(ImageService);

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
