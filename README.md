# Board Game

---

## Overview

This is a solo java based project inspired by the board game, Snakes and Ladders.


This game implements:
- Multiple players
- Dice rolling
- Exact-end win rules
- Hit rules
- Snakes and ladders
- Custom player paths

My project was designed using object-oriented programming principles and rule based architecture.

---

## Features

- 6x6 board
- Multiple players
- Two 6-sided dice
- Snakes and ladders
- Exact end rule
- Path-based movement
- Turn tracking
- Automated tests using JUnit

---

## Architecture

The project is separated into layers:

### Domain
Contains:
- Player
- Board
- SnakeOrLadder
- Rules interfaces

### infrastructure

Contains:
- Rule implementations
- Dice implementations

### Use Case

Contains:
- Game logic
- Application entry point

---

### End Rule
Players must land exactly on the final square to win


### Hit Rule 
If player lands on another player, movement is blocked

### Snakes and Ladders
Landing on Ladder moves player upwards
Landing on Snakes moves player downwards


## Gameplay Example

The game displays player turns, dice rolls, snakes, ladders, and win conditions.

![Gameplay](images/boardgameimage1.png)
![Gameplay](images/boardgameimage2.png)


---
## Testing

The project includes automated JUnit tests for:
- Exact end rule
- Hit rule
- Path movement
- Snakes and ladders

## Test Results

The project includes automated JUnit tests for game rules and movement logic.

![Tests](images/test.png)



---

## Running the Project

Run:

```bash
./mvnw spring-boot:run
```

Or run:

```text
BoardgameApplication
```

from IntelliJ IDEA.

---

## Technologies Used

- Java 21
- Spring Boot
- Maven
- JUnit 5

---

## Author

Dodou Dezoao - 23660651