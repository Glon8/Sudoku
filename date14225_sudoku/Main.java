package OnDate2.date14225_sudoku;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Grid grid = new Grid(81, true);
        Gene gene = new Gene(grid);

        gene.generate();
    }
}

