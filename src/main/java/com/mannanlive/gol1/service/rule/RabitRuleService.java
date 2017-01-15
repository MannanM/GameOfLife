package com.mannanlive.gol1.service.rule;

import com.mannanlive.gol1.state.Cell;
import com.mannanlive.gol1.state.CellState;

public class RabitRuleService implements UniverseRuleService {
    private static final int RABBIT_ONLY_NEED_TWO_PARENT = 2;

    @Override
    public CellState calculatesCellsFuture(Cell cell) {
        if (cell.isAlive()) {
            return aliveCellFutureRules(cell);
        }
        return deadCellFutureRules(cell);
    }

    private CellState deadCellFutureRules(Cell cell) {
        if (cell.getAliveNeighbours() == RABBIT_ONLY_NEED_TWO_PARENT) {
            return CellState.REPRODUCTION;
        }
        return CellState.STILL_DEAD;
    }

    private CellState aliveCellFutureRules(Cell cell) {
        if (cell.getAliveNeighbours() < 2) {
            return CellState.UNDERPOPULATION;
        }
        if (cell.getAliveNeighbours() > 3) {
            return CellState.OVERPOPULATION;
        }
        return CellState.LIVE_ON;
    }
}
