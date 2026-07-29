import { Component, signal } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { RouterLink } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import { ImageViewerComponent } from './components/image-viewer.component';

@Component({
  selector: 'app-root',
  imports: [MatToolbarModule, MatButtonModule, ImageViewerComponent, RouterLink, MatIconModule],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  protected readonly title = signal('angular-learning');
}
