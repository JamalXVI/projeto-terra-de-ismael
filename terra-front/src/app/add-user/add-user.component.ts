import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators, AbstractControl } from '@angular/forms';

import { Md5 } from 'ts-md5/dist/md5';
import { MatSnackBar } from '@angular/material';

import { CustomErrorStateMatcher } from '../core/CustomErrorStateMatcher.model';
import { UserService } from '../core/user/user.service';
import { User } from '../core/user/user.model';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {
  form: FormGroup;
  private matcher = new CustomErrorStateMatcher();
  private roles = [
    {
      name: 'Usuário',
      role: 0
    },
    {
      name: 'Administrador',
      role: 1
    }
  ];
  constructor(private router: Router, private userService: UserService,
    private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.form = new FormGroup({
      id: new FormControl('', [
        Validators.required,
        Validators.pattern('^[1-9][0-9]*$')
      ]),
      name: new FormControl('', [
        Validators.required,
        Validators.minLength(3)
      ]),
      user: new FormControl('', [
        Validators.required,
        Validators.minLength(3)
      ]),
      password: new FormControl('', [
        Validators.required,
        Validators.minLength(3)
      ]),
      confpassword: new FormControl('', [
        Validators.required,
        Validators.minLength(3),
        this.confirmPassword
      ]),
      role: new FormControl('', [
        Validators.required
      ]),
    });
  }
  confirmPassword(c: AbstractControl): { invalidSame: boolean } {
    const values = c.root.value;
    if (values['password'] !== c.value) {
      return { invalidSame: true };
    }
    return null;
  }
  returnList(queryParams?: any) {
    this.router.navigate(['/home'], {queryParams: queryParams});
  }
  sendForm() {
    if (this.form.valid) {
      const users: User[] = this.userService.gerUsers();
      const idError: boolean = users.filter(usr => usr.id === +this.form.value.id).length > 0;
      const usrError: boolean = users.filter(usr => usr.user === this.form.value.user).length > 0;
      if (!idError && !usrError) {
        const md5 = new Md5();
        const newUsr = new User(Object.assign({}, this.form.value, {password: md5.appendStr(this.form.value.password).end()}));
        this.userService.addUser(newUsr);
        this.openSnackBar('Usuário adicionado com sucesso!');
        this.returnList();
      } else {
        if (idError) {
          this.form.controls['id'].setErrors({ alreadyExists: true });
        }
        if (usrError) {
          this.form.controls['user'].setErrors({ alreadyExists: true });
        }
      }
    }
  }
  openSnackBar(message: string) {
    this.snackBar.open(message, '', {
      duration: 1500,
    });
  }
}
