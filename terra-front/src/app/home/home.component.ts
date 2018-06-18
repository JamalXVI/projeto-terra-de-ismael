import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { take, map } from 'rxjs/operators';

import { AuthService } from '../core/auth/auth-service.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  private isAdmin: Boolean = false;
  constructor(private authService: AuthService,
    private router: Router) {
    this.authService.isAdmin().pipe(take(1), map(authState => !!authState)).subscribe(value => {
      this.isAdmin = value;
    });
  }

  ngOnInit() {
  }
  navAddUser() {
    this.router.navigate(['/add']);
  }
}
