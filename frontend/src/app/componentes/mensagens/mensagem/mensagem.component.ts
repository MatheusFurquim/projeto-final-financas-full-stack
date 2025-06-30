import { Component, OnInit } from '@angular/core';
import { NotificationService } from 'src/app/notification.service';

@Component({
  selector: 'app-mensagem',
  templateUrl: './mensagem.component.html',
  styleUrls: ['./mensagem.component.css']
})
export class MensagemComponent implements OnInit {

  mensagem?: string

  constructor(private notificationService: NotificationService) {}

  ngOnInit() {
    this.notificationService.error$.subscribe((error) => {
      this.mensagem = error;
    });
  }

}
