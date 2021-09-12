import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';
import { GameStatistic } from './gamestatistic';
import { environment } from 'src/environments/environment';
import { Game } from './Game';

@Injectable({providedIn: 'root'})
export class GameService {

  private apiServerUrl=environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getGameSummary(): Observable<GameStatistic> {
    return this.http.get<any>(`${this.apiServerUrl}/api/game/game-summary`,{withCredentials: true});
  }

  public startGame(): Observable<Game> {
    return this.http.get<any>(`${this.apiServerUrl}/api/game/start-game`,{withCredentials: true});
  }

  public playRound(): Observable<Game> {
    return this.http.get<any>(`${this.apiServerUrl}/api/game/play-round`,{withCredentials: true});
  }

  public getGame(): Observable<Game> {
    return this.http.get<any>(`${this.apiServerUrl}/api/game/obtain-game`,{withCredentials: true});
  }

}
