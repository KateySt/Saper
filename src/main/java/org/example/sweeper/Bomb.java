package org.example.sweeper;

class Bomb {
    private Matrix bombMap;
    private int totalBomb;

    Bomb(int totalBomb) {
        this.totalBomb = totalBomb;
        fixBombsCount();
    }

    void start() {
        bombMap = new Matrix(Box.ZERO);
        for (int j = 0; j < totalBomb; j++)
            placeBombs();
    }

    private void placeBombs() {
        while (true) {
            Coord coord = Ranges.getRandomCoord();
            if (Box.BOMB == bombMap.get(coord))
                continue;
            bombMap.set(coord, Box.BOMB);
            incNumbersAroundBombs(coord);
            break;
        }

    }

    private void fixBombsCount() {
        int maxBombs = Ranges.getSize().x * Ranges.getSize().y / 2;
        if (totalBomb > maxBombs) {
            totalBomb = maxBombs;
        }
    }

    Box get(Coord coord) {
        return bombMap.get(coord);
    }

    private void incNumbersAroundBombs(Coord coord) {
        for (Coord around : Ranges.getCoordsAround(coord)) {
            if (Box.BOMB != bombMap.get(around)) {
                bombMap.set(around, bombMap.get(around).getNextNumberBox());
            }
        }
    }

    public int getTotalBombs() {
        return totalBomb;
    }
}
