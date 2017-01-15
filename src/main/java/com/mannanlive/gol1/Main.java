package com.mannanlive.gol1;

import com.mannanlive.gol1.service.GameOfLifeService;
import com.mannanlive.gol1.service.UniverseConsoleRenderer;

import java.util.Date;

public class Main {
    private static final int REFRESH_RATE = 500;

    public static void main(String[] args) throws Exception {
        UniverseConsoleRenderer renderer = new UniverseConsoleRenderer();
        GameOfLifeService service = renderer.getUsersUniverse();

        while (true) {
            renderer.toString(service.getUniverse());
            long now = new Date().getTime();
            service.progress();
            long sleepTime = subtractCalculationTimeFromRefreshRate(now);
            Thread.sleep(sleepTime);
        }
    }

    private static long subtractCalculationTimeFromRefreshRate(long now) {
        long remainingTime = REFRESH_RATE - (new Date().getTime() - now);
        return remainingTime < 0 ? 0 : remainingTime;
    }

}
