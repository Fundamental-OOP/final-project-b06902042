# OOP Final Project report

## Member

黃紹輔 B06902013
劉愷為 B06902042
袁其崧 B06902077

## Responsibility

Distribute evenly
We often write it together using vs code live share feature

## Relation Between Classes

### GameController.java

We have two folders, model and ,view and the function like backend and frontend in web programming.
(Model)The GameController news a Board Class which decides what to do with the views when a player action occurs.
(View)It also news a GameView class that deals with the display of the game.

## View

### GameView

In the beginning, we new a MenuView that displays a play button.

After starting a game we remove menuView and new BoardView, Timer,Control that deal with the gameboard it self, the timer in the bottom and Control that deals with points.

### MenuView

Deals with the initial view of the game.

### BoardView

Deals with the display of each gem in the game. Sets their position, size, and all other properties needed for them.

### Timer

deals with the count down and displays the remaining time.

### Control

update the points the player gets after each match of gems and displays.

### Leaderboard

at the end of the game, you can enter your name if you have scored enough points.

## Model

### Board

Deals with what to do with each gem once player action occurs. Such as removing and refilling gems that are matched and calls myTimer when explosion effects are needed.

### Basic

Sets image based on the path given.

### Gem

deals with what should be done with each gem. e.g. whether the gem should be removed or not? Whether the clicked button is the first one clicked or second one. The border displays differently if the button is clicked. Also it checks whether the second button clicked is beside the first one clicked.

### GridBox

stores the value of the position of each gem and upon changing we change their positions.

### MyTimer

Deals the the zoom in of explosion animation after matches of gem.

## Advantages

We try to implement MVC, which can divide the job of every class. We also implement a explosion effect and implement a timer that can count down.

## Disadvantages

Not very familiar with MVC; therefore, our design is a little bit messy
Swap is a little slow

Countless

## How to play

make run.
Don't tell me you don't know how to play candy crush.
