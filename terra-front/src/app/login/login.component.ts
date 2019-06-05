import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormControl, Validators, FormGroup } from '@angular/forms';

import { MatSnackBar } from '@angular/material/snack-bar';

import { CustomErrorStateMatcher } from '../core/CustomErrorStateMatcher.model';
import { AuthService } from '../core/auth/auth-service.service';
import { FrasesService } from '../core/pipes/frases.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  siginForm: FormGroup;

  public matcher = new CustomErrorStateMatcher();
  constructor(private authService: AuthService,
    private router: Router,
    private activedRoute: ActivatedRoute,
    private frasesService: FrasesService,
    private snackBar: MatSnackBar) {
    this.activedRoute.queryParams.subscribe(params => {
      const mensagem = params['mensagem'];
      if (mensagem) {
        switch (mensagem) {
          case 'deslogado':
            const snackBarRef = this.snackBar.open(this.frasesService.converter('SAIR_MENSAGEM'), this.frasesService.converter('SAIR_BOTAO'), { duration: 2500 });
            snackBarRef.onAction().subscribe(act => this.snackBar.open(this.frasesService.converter('SAIR_MENSAGEM_RETORNO'), '',
              { duration: 2000 }));
            break;

          default:
            this.snackBar.open(this.frasesService.converter('ERRO_ACESSO_INVALIDO'), '', { duration: 2500 });
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
      this.authService.logIn(this.siginForm.get('user').value, this.siginForm.get('pwd').value)
        .subscribe(vlr => {
          this.openSnackBar(this.frasesService.converter('LOGIN_SUCESSO'));
          this.router.navigate(['/home']);

        }, err => {
          this.openSnackBar(this.frasesService.converter('LOGIN_FALHA'));

        });
    }
  }
  openSnackBar(message: string) {
    this.snackBar.open(message, '', {
      duration: 1500,
    });
  }
}
