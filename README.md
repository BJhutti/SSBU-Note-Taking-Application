# SSBU Productivity App

## A Java project by Bryan Jhutti

My project will be a **Super Smash Brothers Ultimate Productivity App**. Super Smash Bros. Ultimate is a
fighting game esport where players can choose 1 of over 80 characters to fight with against 
their opponent. Because of this, many top players struggle to retain information about every 
character, and how to fight them with their own character.

My app will aim to help with players. As a top player myself, I know what it is like to write 
notes against each character, and how tedious it can be. Because of this, I was always looking at 
an alternate method I could use to be more efficient, and I believe this 
application will serve that purpose. I also believe this program would be useful for any competitor who 
is looking to create notes for themselves. 

My program will aim to: 
- allow the user to create specific notes against specific characters
- organize notes in a clear to understand way with titles 
- separating notes into character played as, and character played against
- allow for multiple characters 


## User stories 

- As a user, I want to be able to pick my character, select the character I will fight against, and create notes
on that match up. I want to add a list of notes to the enemy character, then add that enemy to a list of enemies for my
character.

- As a user, I want to be able to view every note I have created. 

- As a user, I want to be able to delete notes I've created. 

- As a user, I want to create titles for my notes.

- As a user, I want to be able to save my Character, Enemies, and Notes to a file

- As a user, I want to receive the option to save or not before I quit

- As a user, I want to be able to choose to load my safe file before the application starts, but have the option not to


## Phase 4: Task 2
- Tue Nov 29 19:22:09 PST 2022
- Loading save file from ./data/Bryan's_Notes.json
- Tue Nov 29 19:22:12 PST 2022
- Added Hero to list of characters -
- Tue Nov 29 19:22:17 PST 2022
- Added Villain to Hero's enemies -
-  Tue Nov 29 19:22:31 PST 2022
- Added How to beat Villain to Villain's notes -
-  Tue Nov 29 19:22:35 PST 2022
- Removed How to beat Villain from Villain's notes -
-  Tue Nov 29 19:22:38 PST 2022
- Removed Hero from list of characters -
-  Tue Nov 29 19:22:42 PST 2022
- Saving file to ./data/Bryan's_Notes.json

Note: there may not be any events logged in the case that: 
- the user decides not to load from a save file
- the user decides not to add or remove any characters, notes, or enemies
- the user decides not to save when exiting the app

Otherwise, at least 1 event will always be written 

## Phase 4: Task 3

The first thing that you see in my UML is the amount of classes, as well as the amount of lines
needed for those classes. The way I designed my UI was creating new frames per menu, by extending JFrame.
While this is a good idea, the implementation was poor, as this led to me having to create multiple UI
classes that had many similar aspects. This caused my UML diagram to look cluttered.

To fix this,  I would 
- create a class (lets say AppFrame) that extends JFrame 
- Have it contain the fields that my frames shared (UltCharacter, NoteTakingApp) 
- then allow other frames to extend that AppFrame.

Additionally, to prevent repetition of code I would
- allow the classes that extend AppFrame to override shared methods (ActionPerformed)
- A very specific example of this is the ListEnemies and ListEnemiesForNotes, which function almost the same. I could
  have created a new frame class and allowed each of these to extend that.

This would make my UML clearer, since not every class had to extend of JFrame. It would also get rid of many
repetitive code I had created.

Furthermore, since many of the frames looked similar and functioned similarly, I would
- create a specific frame for menus
- create a specific frame for data entry 
- allow the different types of frames to extend these

Finally, the order in which my code is executed is very hard to follow. I also create many new frame objects which led
to many of my methods looking identical. 
To prevent this I would 
- Create a singular method that takes a JFrame as a parameter, rather than multiple methods for different types
of frames
- Allow more of the execution to be performed within the main app 
