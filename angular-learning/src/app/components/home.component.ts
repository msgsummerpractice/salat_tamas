import { Component } from '@angular/core';
import { ImageViewerComponent } from './image-viewer.component';

@Component({
  selector: 'app-home',
  imports: [ImageViewerComponent],
  templateUrl: './home.component.html',
  styleUrls: ['../app.css', './home.component.css'],
})
export class HomeComponent {}
