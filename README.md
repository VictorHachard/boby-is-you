# BobyIsYou

Boby Is You is a small game copy of [Baba Is You](https://store.steampowered.com/app/736260/Baba_Is_You/). Baba/Boby Is You is an award-winning puzzle game where you can change the rules by which you play. In every level, the rules themselves are present as blocks you can interact with; by manipulating them, you can change how the level works and cause surprising, unexpected interactions! With some simple block-pushing you can turn yourself into a rock, turn patches of grass into dangerously hot obstacles, and even change the goal you need to reach to something entirely different. [link](https://store.steampowered.com/app/736260/Baba_Is_You/)

## Building

This game is develop in java and uses JavaFX. if you want more information there is a report : BobyIsYou.pdf and an diagram UML : BobyIsYou.mdj.

File :

achievement, crash.log, config.txt are created at the root of the project at first launch of the game, their removal remits all the parameters, success and advancement of the player to zero.

Control :

- Arrows is for moving
- R is for restart
- Esc to have the menu
- pageup, pagedown is increase, decrease volume
- De-bug : P, M switch the level
- De-bug : O reset the game mode
- De-bug : L reset the rule

Add a level :

Add a "TXT" file in code / src / common / resources / maps. Then in the Levels java class add in the list, line 146 the name in the char array. In a file of levels, it is imperative to write in the first line the dimension of the map. Then optionally a title (example: title JeSuisUnSuperInformaticien). Always optionally a configuration line with an integer representing the maximum displacement when the game mode limited movement is active (example: config 50).

## Gallery

![screenshot](https://github.com/Glaskani/BobyIsYou/blob/master/sample1.gif)
![screenshot2](https://github.com/Glaskani/BobyIsYou/blob/master/sample2.gif)

## Contributors

Je tiens à remercier toutes les personnes qui ont contribué au projet.

Tout d’abord, pour les nombreux échanges sur la structure et l’implémentions du projet, Pierre Woi, étudiant à HE2B ESI en deuxième année en Informatique Industriel

De plus pour l’environnement du jeu, les musiques, Clément Roart, étudiant à Umons en BAB3 Mathématique. Pour les textures Johan Nevroux, étudiant à l’Human Academy en première année de Manga.

Pour finir, je remercie pour l’aide général qu’il a pu apporté, Thomas Lavend’Homme, étudiant à Umons en première année en Science Informatique.
