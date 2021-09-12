# rock-paper-scissors-exercise

* There is a video (Game Demo.webm) showing how the app looks. Live App
* There is a document (DocumentationAmineKS) explaning what we have done in this exercise and the followed steps


* Steps to run the application locally:

In this case we have two parts:

Backend part: 
* if using an Ide (Eclipse or Intellij), just run the GameApplication.java located in com.amine.ciklumchallenge.controller
* If you prefer launch it without opening any Ide, just run the comand mvnw spring-boot:run from a command line (from the project folder). 

Frontend part
* If you already have Angular installed, you only need to run the commands:
	* npm update from a comand line (from the project folder).
	* ng serve from a comand line (from the project folder).
* If Angular is not installed, you should proceed to install it following these steps:
	* Install Node.js
	* Install Angular CLI (npm install -g @angular/cli)
	* Check if it's already installed (ng –version)
	* Run the command npm update from the project folder.
	* Launch the Frontend project with the command (ng serve) from the project folder.

Running
	* Once both parts (bakend and fronent) are running, open a browser with 	http://localhost:4200 and start playing the rock-scissor-paper game.

Notes: Just to take into consideration:
* Angular project is launched by default in http://localhost:4200
* Angular project is considering that Backend project will be launched in http:localhost:8080. If backend is launched in other url/port, please don't forget to change the url in the file environment.ts located in project_path/src/environments
* Backend project is expecting the angular poject to be launched in http://localhost:4200. The backend project is configured to allow only requests coming from that url. If you prefer to launch the Frontend from other url, please don't forget to add the url to the configuracion file application.properties to the variable cors.allowed.origins (currently it is set like this: cors.allowed.origins=http://localhost:4200)
