import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {AgePredictionModel} from './age-prediction.model';
import {debounceTime, distinctUntilChanged, Observable, shareReplay, Subject, switchMap} from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  static readonly AGE_PREDICTION_URL = 'http://localhost:8080/age/prediction';

  agePrediction$: Observable<AgePredictionModel>;

  private nameSubject = new Subject<string>();

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.agePrediction$ = this.nameSubject.asObservable().pipe(
        debounceTime(300),
        distinctUntilChanged(),
        switchMap(this.getAgePrediction),
        shareReplay()
      );
  }

  /**
   * Fetches the age prediction model from our Spring backend.
   *
   * @param name used for age prediction
   */
  getAgePrediction = (name: string): Observable<AgePredictionModel> => {
    const params = new HttpParams().set('name', name);

    return this.http.get<AgePredictionModel>(AppComponent.AGE_PREDICTION_URL,
      {params});
  }

  onNameChanged($event) {
    this.nameSubject.next($event.target.value);
  }
}
