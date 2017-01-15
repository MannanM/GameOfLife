package com.mannanlive.gol1.state;

import java.util.List;

public class Universe {
    private List<List<Cell>> universe;
    private int tick;
    private int liveCells;

    public Universe(List<List<Cell>> universe, int tick, int liveCells) {
        this.universe = universe;
        this.tick = tick;
        this.liveCells = liveCells;
    }

    public Cell getCellWithNeighbourCount(int x, int y) {
        int aliveCellNeighbours = getAliveCellNeighbours(x, y);
        Cell cell = getCell(x, y);
        cell.setAliveNeighbours(aliveCellNeighbours);
        return cell;
    }

    private Cell getCell(int x, int y) {
        return universe.get(x).get(y);
    }

    //exposed for testing
    int getAliveCellNeighbours(int x, int y) {
        int result = 0;
        for (int i = x - 1; i < x + 2; i++) {
            for (int j = y - 1; j < y + 2; j++) {
                if (skipableCell(x, y, i, j)) {
                    continue;
                }
                if (getCell(i, j).isAlive()) {
                    result++;
                }
            }
        }
        return result;
    }

    private boolean skipableCell(int x, int y, int i, int j) {
        return targetCell(x, y, i, j) || lessThan0(i, j) || moreThanBounds(i, j);
    }

    private boolean moreThanBounds(int i, int j) {
        return i >= universe.size() || j >= universe.get(0).size();
    }

    private boolean lessThan0(int i, int j) {
        return i < 0 || j < 0;
    }

    private boolean targetCell(int x, int y, int i, int j) {
        return i == x && j == y;
    }

    public List<List<Cell>> getUniverse() {
        return universe;
    }

    public int getTick() {
        return tick;
    }

    public int getLiveCells() {
        return liveCells;
    }

}
