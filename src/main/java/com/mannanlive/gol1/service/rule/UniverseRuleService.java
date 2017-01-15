package com.mannanlive.gol1.service.rule;

import com.mannanlive.gol1.state.Cell;
import com.mannanlive.gol1.state.CellState;

public interface UniverseRuleService {
    CellState calculatesCellsFuture(Cell cell);
}
