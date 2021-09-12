import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { GameService } from '../game.service';
import { GameStatistic } from '../gamestatistic';


@Component({
  selector: 'app-game-summary',
  templateUrl: './game-summary.component.html',
  styleUrls: ['./game-summary.component.css']
})
export class GameSummaryComponent implements OnInit {

  public gameStatistics!: GameStatistic;

  public summaryHeaders = ["Total played", "Rounds won by player1", "Rounds won by player2", "Draws"];

  constructor (private gameService : GameService){}

  ngOnInit(): void {
   this.getGameSummary()
  }

  public getGameSummary(): void {
    this.gameService.getGameSummary().subscribe(
      (response: GameStatistic) =>{
        this.gameStatistics = response
      },
      (error: HttpErrorResponse) =>{
        if(!error.error.message){
          alert("Unkwon error. Please check if your server is up and your backend application is running in the right port")
        }else{
          alert(error.error.message)
        }
      }
    )
  }
}
