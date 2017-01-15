package com.mannanlive.gol1.service.rule;

import com.mannanlive.gol1.state.Cell;
import com.mannanlive.gol1.state.CellState;

public class HermitRuleService implements UniverseRuleService {
    private static final int HERMIT_DOESNT_NEED_NEIGHBOUR = 1;

    @Override
    public CellState calculatesCellsFuture(Cell cell) {
        if (cell.isAlive()) {
            return aliveCellFutureRules(cell);
        }
        return deadCellFutureRules(cell);
    }

    private CellState deadCellFutureRules(Cell cell) {
        if (cell.getAliveNeighbours() == 3) {
            return CellState.REPRODUCTION;
        }
        return CellState.STILL_DEAD;
    }

    private CellState aliveCellFutureRules(Cell cell) {
        if (cell.getAliveNeighbours() < HERMIT_DOESNT_NEED_NEIGHBOUR) {
            return CellState.UNDERPOPULATION;
        }
        if (cell.getAliveNeighbours() > 3) {
            return CellState.OVERPOPULATION;
        }
        return CellState.LIVE_ON;
    }
}
