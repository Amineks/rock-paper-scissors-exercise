import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Game } from '../Game';
import { GameService } from '../game.service';

@Component({
  selector: 'app-game-playing',
  templateUrl: './game-playing.component.html',
  styleUrls: ['./game-playing.component.css']
})
export class GamePlayingComponent implements OnInit {

  public game!: Game;

  public roundCounter=0;

  public headers = ["Round", "Player1 move", "Player2 move", "Round result"];
  
  constructor (private gameService : GameService){}

  ngOnInit(): void {
   this.getGame();
  
  }

  public startGame(): void {
     this.gameService.startGame().subscribe(
        (response: Game) =>{
          this.game = response
          
          if(!!this.game.rounds){
            this.roundCounter = this.game.rounds.length
          }else{
            this.roundCounter = 0
          }
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
       
    public playRound(): void {
      this.gameService.playRound().subscribe(
        (response: Game) =>{
           this.game.rounds= response.rounds
          this.roundCounter = this.game.rounds.length
        
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


    public getGame(): void {
      this.gameService.getGame().subscribe(
        (response: Game) =>{
          if(response==null){
            this.startGame()
          }else{
          this.game=response
          this.game.rounds= response.rounds
          this.roundCounter = this.game.rounds.length
        }
          
        },
        (error: HttpErrorResponse) =>{
          if(!error.error.message){
            alert("Unkwon error. Please check if your server is up and your backend application is running in the right port")
          }else if(error.status== 404){ //first time the page is loaded, there is no started game, we start a new one by default
            this.startGame()
          }else{
            alert(error.error.message)
          }
        }
      )
    }

}
