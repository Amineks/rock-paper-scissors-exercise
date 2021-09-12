import { Move } from "./Move";
import { Result } from "./Result";

export interface Round{
      movePlayerOne: Move;
      movePlayerTwo: Move;
      roundResult: Result;
}