import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {map, catchError} from "rxjs/operators";
import { Observable } from 'rxjs';

@Injectable()
export class AuthenticationService {
    static LOGIN_URL = "http://localhost:8081/tp/api/login";
    static CURRENT_USER_STORAGE_KEY = "currentUser";

    constructor(private http: HttpClient) {}
    
    login(username: string, password: string) {
       return this.http.post(AuthenticationService.LOGIN_URL, {username: username, password: password})
      .toPromise()
      .then(
        res => { // Success
          if(res && res['access_token']) {
            localStorage.setItem(AuthenticationService.CURRENT_USER_STORAGE_KEY, JSON.stringify(res));
          }
          console.log(this.getUserId());
        }
      );
    }

    logout() {
        localStorage.removeItem(AuthenticationService.CURRENT_USER_STORAGE_KEY);
    }

    getUsername() {
        let session = this.getSession();
        return session && session.username;
    }

    getAccessToken() {
        let session = this.getSession();
        if(!session) {
            return null;
        }
        return session && session.access_token;
    }

    private getSession() {
        let sessionStr = localStorage.getItem(AuthenticationService.CURRENT_USER_STORAGE_KEY);
        return JSON.parse(sessionStr);
    }

    private getUserId() {
        let session = this.getSession();
        if(!session) {
            return null;
        }
        return session;
    }


}