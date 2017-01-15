# GameOfLife

Java (Console) implementation of Conway's Game of Life.

## Background

Wanted to try out what sort of implementation I would come up with purely on my own without looking at anything other than the [Wikipedia Page](https://en.wikipedia.org/wiki/Conway's_Game_of_Life). This took a bit over 2.5 hours include setting up the GitHub and creating the readme.

This implementation just uses the terminal to show the behaviour of live cells ("O") after an initial population has been seeded. It refreshes every 500ms automatically, so `ctrl + c` to exit.

## Features

 * Customisable size of the universe (simple width & height).
 * Current seed of the universe is randomised by a customisable percentage.
 * Three display modes are available:
   * Normal (live cell = "0", dead cell = " ")
   * Complex (reproduction = "o", live-on = "O", overpopulation = "X", underpopulation = "-")
   * Colour (reproduction = green, overpopulation = red, underpopulation = cyan)
 * Three Universe Rules are available, and any number should be very easy to implement:
   * Default: Conway's Games of Life rules implemented verbatim
   * Rabbit: Same as default, but only need two parents to reproduce
   * Hermit: Same as default, but alive cells don't need any active neighbours to survive
 * Game & Display Logic is pretty much completely separated

## Known Issues

 * Screen clear only works on some terminals (e.g. cmd is ok but IntelliJ isn't)
 * Colour mode only works on some terminals (e.g. IntelliJ is ok but cmd isn't)
 * Missing tests for practically everything :(
 * The dimensions of the universe isn't encapsulated neatly and are both in the service and the state
 * The number of live cells and universe ticks also could be better stored somewhere
