import { Component } from '@angular/core';
import { tokenGetter } from './app.module';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  logado() {
    const token = tokenGetter()
    return token != null
  }

}
