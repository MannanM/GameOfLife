package com.mannanlive.gol1.state;

public class Cell {
    private CellState state;
    private int aliveNeighbours;

    public Cell(CellState cellsFuture) {
        state = cellsFuture;
    }

    public boolean isAlive() {
        return state == CellState.LIVE_ON || state == CellState.REPRODUCTION;
    }

    public CellState getState() {
        return state;
    }

    public int getAliveNeighbours() {
        return aliveNeighbours;
    }

    void setAliveNeighbours(int aliveNeighbours) {
        this.aliveNeighbours = aliveNeighbours;
    }
}
