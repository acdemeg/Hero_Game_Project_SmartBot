package com.neolab.heroesGame.smartBots;

public class WinCollector {
    private static final double EPS = 1.0e-9;
    private double win;
    private double drawWin;
    private double pLoose;
    private double pWin;
    private double pDraw;

    WinCollector(final double win,
                 final double drawWin,
                 final double pLoose,
                 final double pWin,
                 final double pDraw) {
        this.win = win;
        this.drawWin = drawWin;
        this.pLoose = pLoose;
        this.pWin = pWin;
        this.pDraw = pDraw;
    }

    public void add(final WinCollector w, final double p) {
        win += p * w.win;
        drawWin += p * w.drawWin;
        pLoose += p * w.pLoose;
        pWin += p * w.pWin;
        pDraw += p * w.pDraw;
    }

    public double getTotalWin() {
        return win + drawWin;
    }

    public WinCollector catchBrokenProbabilities() {
        final double sumP = pDraw + pWin + pLoose;
        if (Math.abs(sumP - 1.0D) > EPS) {
            throw new IllegalStateException("Sum proba != 1: " + sumP);
        }
        return this;
    }

    @Override
    public String toString() {
        return String.format("totalWin = %.2f win=%.2f, drawWin=%.2f, pLoose=%.2f, pWin=%.2f, pDraw=%.2f",
                getTotalWin(),
                win,
                drawWin,
                pLoose,
                pWin,
                pDraw);
    }
}
