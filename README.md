# Reversi
Created a 2-player Reversi Game

### Objective
The goal is to have more of your pieces on the board than your opponent by the time the game ends.

### Setup
Board: 8×8 grid.
Pieces: two-sided discs (black and white).
Initial position:
. . . . . . . .
. . . . . . . .
. . . . . . . .
. . . W B . . .
. . . B W . . .
. . . . . . . .
. . . . . . . .
. . . . . . . .
(W = White, B = Black)
White moves first.

### How to Play
Place a piece on an empty square such that it flanks one or more opponent pieces in a straight line (horizontal, vertical, or diagonal).
Flip all opponent pieces that are flanked between your new piece and another of your pieces in that line.
If a player cannot make a valid move, they pass their turn.
The game ends when no one can move (usually when the board is full or no valid moves exist for either player).

### Rules for Valid Moves
You must capture at least one opponent piece on your turn.
Capturing can happen in multiple directions at once.
If no valid move exists, you must pass, even if you don’t like it.

### Scoring
Count pieces of each colour on the board at the end.
The player with more pieces wins.
