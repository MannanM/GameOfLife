package com.mannanlive.gol1.service;

import com.mannanlive.gol1.service.rule.DefaultRuleService;
import com.mannanlive.gol1.service.rule.HermitRuleService;
import com.mannanlive.gol1.service.rule.RabitRuleService;
import com.mannanlive.gol1.service.rule.UniverseRuleService;
import com.mannanlive.gol1.state.Cell;
import com.mannanlive.gol1.state.CellState;
import com.mannanlive.gol1.state.Universe;

import java.util.List;
import java.util.Scanner;

public class UniverseConsoleRenderer {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String SPACE = " ";
    private int displayMode;

    public GameOfLifeService getUsersUniverse() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the width of the universe: ");
        int width = getNumber(scanner);
        System.out.println("Please enter the height of the universe: ");
        int height = getNumber(scanner);
        System.out.println("Please enter the initial population % of the universe: ");
        int initialPopulation = getNumber(scanner);
        System.out.println("Please enter display mode (0 = normal, 1 = complex, 2 = coloured): ");
        displayMode = getNumber(scanner);
        UniverseRuleService universeRuleService = getUniverseRuleService(scanner);

        return new GameOfLifeService(width, height, initialPopulation, universeRuleService);
    }

    private UniverseRuleService getUniverseRuleService(Scanner scanner) {
        System.out.println("Please enter universe rules (0 = normal, 1 = rabbit, 2 = hermit): ");
        UniverseRuleService universeRuleService = new DefaultRuleService();
        int rules = getNumber(scanner);
        if (rules > 0) {
            universeRuleService = rules == 1 ? new RabitRuleService() : new HermitRuleService();
        }
        return universeRuleService;
    }

    public void toString(Universe universe) {
        clearScreen();

        StringBuilder stringBuilder = writeUniverse(universe);

        stringBuilder.append(String.format("\r\nDate of Universe: %d", universe.getTick()));
        stringBuilder.append(String.format("\r\nNumb. Live Cells: %d", universe.getLiveCells()));

        System.out.println(stringBuilder.toString());
    }

    private StringBuilder writeUniverse(Universe universe) {

        int width = universe.getUniverse().get(0).size();
        StringBuilder stringBuilder = new StringBuilder();
        headerRow(stringBuilder, width);
        for (List<Cell> row : universe.getUniverse()) {
            stringBuilder.append('|');
            for (Cell cell : row) {
                stringBuilder.append(getCellStateString(cell.getState()));
            }
            stringBuilder.append("|\r\n");
        }
        headerRow(stringBuilder, width);
        return stringBuilder;
    }

    private String getCellStateString(CellState state) {
        switch (displayMode) {
            case 1:
                return getCellStateStringExtended(state);
            case 2:
                return getCellStateStringColoured(state);
            default:
                return getCellStateStringSimple(state);
        }
    }

    private String getCellStateStringSimple(CellState state) {
        switch (state) {
            case LIVE_ON:
            case REPRODUCTION:
                return "O";
            default:
                return SPACE;
        }
    }

    private String getCellStateStringExtended(CellState state) {
        switch (state) {
            case REPRODUCTION:
                return "o";
            case OVERPOPULATION:
                return "X";
            case UNDERPOPULATION:
                return "-";
            case LIVE_ON:
                return "O";
            default:
                return SPACE;
        }
    }

    private String getCellStateStringColoured(CellState state) {
        switch (state) {
            case REPRODUCTION:
                return ANSI_GREEN + "O" + ANSI_RESET;
            case OVERPOPULATION:
                return ANSI_RED + "O" + ANSI_RESET;
            case UNDERPOPULATION:
                return ANSI_CYAN + "O" + ANSI_RESET;
            case LIVE_ON:
                return "O";
            default:
                return SPACE;
        }
    }

    private void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception ignored) {
            //probably on linux
        }
    }

    private static int getNumber(Scanner scanner) {
        String s = scanner.nextLine();
        try {
            int i = Integer.parseInt(s);
            if (i < 0) {
                System.out.println("Please enter a positive number e.g 30");
                return getNumber(scanner);
            }
            return i;
        } catch (Exception ex) {
            System.out.println("Please try again and enter a number e.g. 65");
            return getNumber(scanner);
        }
    }

    private void headerRow(StringBuilder stringBuilder, int size) {
        for (int i = 0; i < size + 2; i++) {
            stringBuilder.append('-');
        }
        stringBuilder.append("\r\n");
    }
}
