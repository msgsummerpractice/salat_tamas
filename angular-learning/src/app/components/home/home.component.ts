import { Component } from '@angular/core';
import { ImageViewerComponent } from '../image-viewer/image-viewer.component';
import { ShortenPipe } from '../../pipes/shorten.pipe';

@Component({
  selector: 'app-home',
  imports: [ImageViewerComponent, ShortenPipe],
  templateUrl: './home.component.html',
  styleUrls: ['../../app.css', './home.component.css'],
})
export class HomeComponent {}
