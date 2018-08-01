import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AuthService } from '../core/auth/auth-service.service';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.css']
})
export class ToolbarComponent implements OnInit {
  username: String = '';
  constructor(private authService: AuthService,
    private router: Router) {
    this.authService.getUserName().subscribe(usr =>this.username = usr.toUpperCase());
  }

  ngOnInit() {
  }
  logout() {
    this.authService.logout();
    this.router.navigate(['/login'], { queryParams: { mensagem: 'deslogado' } });
  }
}
