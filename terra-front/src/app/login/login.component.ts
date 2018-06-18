import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormControl, Validators, FormGroup } from '@angular/forms';

import { MatSnackBar } from '@angular/material/snack-bar';

import { CustomErrorStateMatcher } from '../core/CustomErrorStateMatcher.model';
import { AuthService } from '../core/auth/auth-service.service';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  siginForm: FormGroup;

  private matcher = new CustomErrorStateMatcher();
  constructor(private authService: AuthService,
    private router: Router,
    private activedRoute: ActivatedRoute,
    private snackBar: MatSnackBar) {
    this.activedRoute.queryParams.subscribe(params => {
      const mensagem = params['mensagem'];
      if (mensagem) {
        switch (mensagem) {
          case 'deslogado':
            const snackBarRef = this.snackBar.open('Então é assim? Então vai! *cries*', 'Pedir Desculpas', { duration: 2500 });
            snackBarRef.onAction().subscribe(act => this.snackBar.open('Desculpado, mas não quero me magoar novamente, ta?', '',
              { duration: 2000 }));
            break;

          default:
            this.snackBar.open('Aha! Parece que alguém estava tentando acessar sem estar logado, né?', '', { duration: 2500 });
            break;
        }
      }
    });
  }

  ngOnInit() {
    this.siginForm = new FormGroup({
      user: new FormControl('', [
        Validators.required,
        Validators.minLength(3)
      ]),
      pwd: new FormControl('', [
        Validators.required,
        Validators.minLength(3)
      ])
    });
  }
  public sendForm() {
    if (this.siginForm.valid) {
      if (this.authService.logIn(this.siginForm.get('user').value, this.siginForm.get('pwd').value)) {
        this.openSnackBar('Quem procura acha né homi?');
        this.router.navigate(['/home']);
      } else {
        this.openSnackBar('Oxi, encontrei foi não! Tem certeza que está certo ai?');
      }
    }
  }
  openSnackBar(message: string) {
    this.snackBar.open(message, '', {
      duration: 1500,
    });
  }
}
