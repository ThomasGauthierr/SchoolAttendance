import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {map, catchError} from "rxjs/operators";
import { Observable } from 'rxjs';
import { AuthenticationService } from '../login/authentication.service';

@Injectable()
export class CoursService {
    static COURSE_URL = "http://localhost:8081/tp/api/course";
    static STUDENTS_URL = "http://localhost:8081/tp/api/student";
    
    constructor(private http: HttpClient,
        private authenticationService: AuthenticationService) {
    }

    getCours(username) {
        return this.http.get(CoursService.COURSE_URL, {
            headers: {'X-Auth-Token': this.authenticationService.getAccessToken()}
        })
        .toPromise();
    }

    getStudents() {
        return this.http.get(CoursService.STUDENTS_URL, {
            headers: {'X-Auth-Token': this.authenticationService.getAccessToken()}
        })
        .toPromise();
    }

    addCours(name, description, teacher) {
        return this.http.post(CoursService.COURSE_URL, 
            {name: name, description: description, teacher: teacher}, 
            {headers: {'X-Auth-Token': this.authenticationService.getAccessToken()}
        })
        .toPromise();
    }
}