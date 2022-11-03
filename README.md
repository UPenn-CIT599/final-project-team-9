# final-project-team-9


Overview:

The game is a single player game that has a layer of bricks that are arranged in rows with a goal to destroy the bricks with a ball. The ball moves around the screen bouncing off of a paddle, the top, and two sides of the screen. When the brick is hit, the ball bounces back and the brick is destroyed (the strength of the brick determines how quickly the brick is destroyed). There are three difficulty types to choose from and each difficulty has three levels. The game continues until all bricks are destroyed at each level or the ball falls below the paddle a total of three times. 

Library used and how to pull into Eclipse:

For our project we used the javax.swing library, which is already included in Eclipse so you do not need to download / import a library. Our java files are located within the SRC folder within the github repository (https://github.com/UPenn-CIT599/final-project-team-9). You will also need to pull in the music files, the high score list txt file, and the high score test list txt file that are located in the main project folder. 

Once you have pulled everything in from github, if you navigate to the Runner.java file, you will be able to run the game. Upon running the code, you will be prompted with a screen like this: 

![alt text](https://github.com/UPenn-CIT599/final-project-team-9/blob/master/Screen%20Shot%202020-04-23%20at%204.33.00%20PM.png)

Enter your name and select the difficulty you would like to play at and you are all set!

The game play screen will look like this if you select ‘Medium’: 

![alt text](https://github.com/UPenn-CIT599/final-project-team-9/blob/master/Screen%20Shot%202020-04-23%20at%204.34.15%20PM.png)

You will need to press ‘p’ to play the game. You can press ‘p’ to also pause game play. 

Another view of the game if you select ‘Hard’:

![alt text](https://github.com/UPenn-CIT599/final-project-team-9/blob/master/Screen%20Shot%202020-04-23%20at%204.53.46%20PM.png)

When game play is over, you will receive this message and the Main Menu screen will re-appear: 

![alt text](https://github.com/UPenn-CIT599/final-project-team-9/blob/master/Screen%20Shot%202020-04-23%20at%204.40.31%20PM.png)

Functionalities of game:

  Bricks: 
    1) Random array of bricks displayed each time
    2) Gray bricks which act like walls
    3) Powerups unlocks when the player continuously plays a certain increment without losing lives. These include:
      a)Bricks destroyer - deliver 1 hit to all bricks
      b)Wall destroyer - removes all gray bricks - only applicable in medium/hard
      
  Sound: both background and sound effects
  
  Levels: each gameplay allows for three levels of continuous play
  
  Pause/Play: game allows for pausing/resuming game
  
  High Score: list that is kept and updated after each game play
  
  Score, Level, Difficulty, Player Name, Lives: all displayed on GUI and updated 
  
  Continuous play: after program launch, the following cycle occurs until exited by user:
    1) Entering name
    2) Selecting a level
    3) Playing each level, passing through all the way OR playing each level and losing all three lives
    4) GUI updates to show game over and conditionally win and high score status
    5) Return to main menu for next game
