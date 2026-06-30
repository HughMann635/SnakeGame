## **Snake in Java, by Zahran**
A  retro Snake game built entirely in Java. Lets you adjust speed, alter board dimensions, and toggle collisions and randomly spawning mines!

https://github.com/user-attachments/assets/f2301898-39c5-4a6c-b7a6-d49576c88659

## Downloading
Download **SnakeMines.jar** below to play instantly!
**Java Runtime Environment (JRE) 8 or higher** must be installed on your computer.

### *How to Run*
1. Download the `SnakeMines.jar` file from the Assets section below.
2. Double-click the file to launch the game.
3. *If double-clicking doesn't work:* Open your terminal/command prompt, navigate to your downloads folder, and run:

   ```bash
   java -jar SnakeMines.jar

## Features & Controls

### Customization Menu
* **Speed Adjustments:** SLOW (200ms delay), MEDIUM (120ms delay), FAST (70ms delay)
* **Board Dimensions:** 10x10, 15x15, or 20x20 grids
* **Modifiers:**
  * **MINES:** Spawns explosive mines every 2 apples.
  * **CHILL:** Toggles wall-collisions off. You can phase through yourself, or phase through walls and loop back on the opposite side.
  * **RESET:** Restores original settings.

### Controls
* **Move:** `Arrow Keys` or `W, A, S, D`
* **Self-Destruct (Reset):** `R`
* **Menu Selection:** Left-click on buttons via Mouse

## How it Works
My Snake game uses coordinates on a grid to calculate positions rather than exact locations. Every coordinate is stored as an integer between 1 and *boardDim*, which is a variable equal to the length/width of the board. Scaling to actual screen pixels is separate from game logic and only processed when rendering in **paintComponent**(the graphics function). This simplifies collision detection and makes it easy to scale the board and related entities without requiring complex math.

The game also implements a feature to stop you from dying after you mash two inputs. The first input disables all directional events that happen afterwards until the next frame. This  eliminates self-collision caused by frame rate issues.

## References
Video reference: https://www.youtube.com/watch?v=bI6e6qjJ8JQ**

I used this video as a reference for the setup of my game. However, the main menu, modifiers, and majority of gameplay were developed by me.
I used the video to get an idea of how to setup files, and referenced it whenever I was stuck on how to make a certain feature work.

Additionally, I occassionally used Google Gemini to help me debug and fix a few aspects of my code.
The most notable AI section is the contains() function, which is used to detect collisions and ensure that apples did not overlap the snake.


*This project was created and submitted to Hack Club Stardance 2026 by Zahran G.*
