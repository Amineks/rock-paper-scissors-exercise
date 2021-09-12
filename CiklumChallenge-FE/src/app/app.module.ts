import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { GameService } from './game.service';
import { GamePlayingComponent } from './game-playing/game-playing.component';
import { GameSummaryComponent } from './game-summary/game-summary.component';

@NgModule({
  declarations: [
    AppComponent,
    GamePlayingComponent,
    GameSummaryComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot([
      {path: 'game-playing', component: GamePlayingComponent},
      {path: 'game-summary', component: GameSummaryComponent},
      {path: '', redirectTo: '/game-playing', pathMatch: 'full'},
    ]),
  ],
  providers: [GameService],
  bootstrap: [AppComponent]
})
export class AppModule { }
