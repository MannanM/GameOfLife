package com.mannanlive.gol1.service;

import com.mannanlive.gol1.service.rule.UniverseRuleService;
import com.mannanlive.gol1.state.Cell;
import com.mannanlive.gol1.state.CellState;
import com.mannanlive.gol1.state.Universe;

import java.util.ArrayList;
import java.util.List;

public class GameOfLifeService {
    private UniverseRuleService ruleService;
    private Universe universe;
    private int width;
    private int height;

    public GameOfLifeService(int width, int height, int populationPercent, UniverseRuleService ruleService) {
        this.width = width;
        this.height = height;
        this.ruleService = ruleService;
        universe = createInitialUniverseWithSeed(populationPercent);
    }

    public void progress() {
        int liveCells = 0;

        List<List<Cell>> newUniverseState = new ArrayList<>(height);
        for (int i = 0; i < height; i++) {
            ArrayList<Cell> row = new ArrayList<>(width);
            for (int j = 0; j < width; j++) {
                Cell cellWithNeighbourCount = universe.getCellWithNeighbourCount(i, j);
                CellState cellState = ruleService.calculatesCellsFuture(cellWithNeighbourCount);
                Cell cell = new Cell(cellState);
                liveCells += cell.isAlive() ? 1 : 0;
                row.add(cell);
            }
            newUniverseState.add(row);
        }
        this.universe = new Universe(newUniverseState, universe.getTick() + 1, liveCells);
    }

    public Universe getUniverse() {
        return universe;
    }

    private Universe createInitialUniverseWithSeed(int percent) {
        int liveCells = 0;
        List<List<Cell>> universeState = new ArrayList<>(height);
        for (int i = 0; i < height; i++) {
            ArrayList<Cell> row = new ArrayList<>(width);
            for (int j = 0; j < width; j++) {
                boolean alive = Math.random() < (percent / 100.0);
                liveCells += alive ? 1 : 0;
                row.add(new Cell(alive ? CellState.REPRODUCTION : CellState.STILL_DEAD));
            }
            universeState.add(row);
        }
        return new Universe(universeState, 0, liveCells);
    }
}
