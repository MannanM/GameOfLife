package com.mannanlive.gol1.state;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UniverseTest {

    @Test
    public void getAliveCellNeighboursReturnsMax8() throws Exception {
        Universe state = createUniverse(3, true);

        int aliveCellNeighbours = state.getAliveCellNeighbours(1, 1);
        Assert.assertEquals(8, aliveCellNeighbours);
    }

    @Test
    public void getAliveCellNeighboursReturnsMin0() throws Exception {
        Universe state = createUniverse(3, false);

        int aliveCellNeighbours = state.getAliveCellNeighbours(1, 1);
        Assert.assertEquals(0, aliveCellNeighbours);
    }

    private Universe createUniverse(int size, boolean alive) {
        ArrayList<List<Cell>> universe = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            universe.add(new ArrayList<>(size));
            for (int j = 0; j < size; j++) {
                universe.get(i).add(new Cell(alive ? CellState.REPRODUCTION : CellState.STILL_DEAD));
            }
        }
        return new Universe(universe, 0, alive ? 9 : 0);
    }

}