import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';
import { CoursService } from './cours.service';
import { AuthenticationService } from '../login/authentication.service';
import { Observable } from 'rxjs';
import { interval } from 'rxjs';

@Component({
  selector: 'app-cours',
  templateUrl: './cours.component.html',
  styleUrls: ['./cours.component.scss']
})
export class CoursComponent implements OnInit {

    private cours = []
    private students = [];
    private newCoursName;
    private newCoursDescription;
    private sub;

    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private coursService: CoursService,
        private authenticationService: AuthenticationService) {
        }

    ngOnInit() {
        this.coursService.getCours("username").then(
            res => {
              let cours = JSON.parse(JSON.stringify(res));
              cours = cours.filter(cours => cours.teacher == this.authenticationService.getUsername());
              console.log('The courses');
              console.log(cours);
              if(cours)
                cours.forEach(element => {
                    this.cours.push(element);
                });
            }
        );

        this.coursService.getStudents().then(
            res => {
                let students = JSON.parse(JSON.stringify(res));
                this.students = students;
                console.log(this.students);
            }
        );
        
        this.sub = interval(30000)
        .subscribe((val) => { this.poll() });
    }

    addCours() {
        console.log(this.newCoursName);
        console.log(this.newCoursDescription);
        console.log(this.authenticationService.getUsername());
        this.coursService.addCours(this.newCoursName, this.newCoursDescription, this.authenticationService.getUsername()).then(
            res => {
                this.poll();
                this.newCoursDescription = null;
                this.newCoursName = null;
            }
        )
    }

    poll() {
        this.cours.splice(0, this.cours.length);
        this.coursService.getCours("username").then(
            res => {
                let cours = JSON.parse(JSON.stringify(res));
                console.log("The cours");
                for(let cour of cours) {
                    console.log(this.cours.filter(e => e.name === cour.name));
                   // if(this.cours.filter(e => e.name === cour.name).length === 0) {
                        this.cours.push(cour);
                    //}
                }
                
            }
        );
    }

    isPresent(session, student) {
        /*if(this.cours.participations.map(e => e.firstName).includes(student)) {
            return 'Present';
        }*/
        console.log(session.participations.map(e => e.student.firstName));
        console.log('The first name');
        console.log(student);
        if(session.participations.map(e => e.student.firstName).includes(student) ) {
            return 'Present'
        }
        return 'Absent';
    }
}
