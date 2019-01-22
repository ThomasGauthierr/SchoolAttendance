import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';
import { CoursService } from './cours.service';
import { AuthenticationService } from '../login/authentication.service';
import { Observable } from 'rxjs';
import { interval } from 'rxjs';
import { getLocaleDayNames } from '@angular/common';

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
    private date;
    private time;
    private timeEnd;
    private dateEnd;
    
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
;
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
            }
        );
        
        this.sub = interval(30000)
        .subscribe((val) => { this.poll() });
    }

    addCours() {
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
                for(let cour of cours) {
                   // if(this.cours.filter(e => e.name === cour.name).length === 0) {
                        this.cours.push(cour);
                    //}
                }
                
            }
        );
    }

    isPresent(session, student) {
        if(session.participations.map(e => e.student.firstName).includes(student) ) {
            return 'Present'
        }
        return 'Absent';
    }

    addSession() {
        console.log(this.getHour(this.time));
        console.log(this.getMinutes(this.time));


    }

    addSeance(cours) {
        console.log("ajouter seance");
        this.coursService.addSeance(cours.id, this.formatDate(this.date, this.time), this.formatDate(this.dateEnd, this.timeEnd))
            .then(res => {
                this.poll();
            });
    }

    private getDay(date) {
        return date.substring(8, 10);
    }

    private getMonth(date) {
        return date.substring(5, 7);

    }

    private getYear(date) {
        return date.substring(0, 4);
    }

    private getHour(time) {
        return time.substring(0, 2);
    }

    private getMinutes(time) {
        return time.substring(3, 5);
    }

    private formatDate(date, time) {
        return this.getDay(date) + '-' + this.getMonth(date) + '-' + this.getYear(date) + ' ' + this.getHour(time) + '-' + this.getMinutes(time);
    }

}