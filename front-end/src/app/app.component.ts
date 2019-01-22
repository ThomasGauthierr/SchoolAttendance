import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { AuthenticationService } from './login/authentication.service';


@Component({
  selector: 'app-app',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  loginForm: FormGroup;
    loading = false;
    submitted = false;
    returnUrl: string;
    username: string;
    password: string;

    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private spinnerService: Ng4LoadingSpinnerService,
        private authenticationService: AuthenticationService) {}

    ngOnInit() {
        this.loginForm = this.formBuilder.group({
            username: ['', Validators.required],
            password: ['', Validators.required]
        });

        // reset login status
        this.authenticationService.logout();

        // get return url from route parameters or default to '/'
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    }

    // convenience getter for easy access to form fields
    get f() { return this.loginForm.controls; }

    onSubmit() {
        console.log(this.loginForm.controls['username'].value);

      this.spinnerService.show();

        this.submitted = true;

        // stop here if form is invalid
        if (this.loginForm.invalid) {
        //    return;
        }

        this.loading = true;

        console.log(this.username);
        this.authenticationService.login(this.username, this.password).then(res => {
            setTimeout(() => {
                this.spinnerService.hide();
                this.router.navigate(['cours']);
              }, 1500);

            
        });
    }
}
