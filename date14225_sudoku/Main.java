package OnDate2.date14225_sudoku;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Grid grid = new Grid(81, true);

        int[][] rows = grid.toRows();
        int[][] cols = grid.toColumns();
        int[][] squares = grid.toSquares();

        System.out.println(grid);

        System.out.println("Rows");
        print(grid, rows);
        System.out.println("Columns");
        print(grid, cols);
        System.out.println("Squares");
        print(grid, squares);

        System.out.println("The index 60 valid is " + isValid_NoValue(grid, 60));

        System.out.println("The index 60 valid is " + isValid(grid, 60, 58));

        System.out.println(grid);

        sudokuCreate(grid);
    }

    public static void sudokuCreate(Grid grid) {
        grid.clearGrid();

        int[][] squares = grid.toSquares();

        boolean[] validityForm = new boolean[squares.length];

        boolean breakPoint = false;

        for (int i = 1; i <= 9; i++) {
            for (int g = 0; g < squares.length; g++) {
                Random rnd = new Random();

                updateValidity(validityForm, grid, squares[g], i);

                int[] valInd = validityToInt(validityForm);

                System.out.println("number: " + i + "   square: " + g);
                System.out.println(grid);

                int min = 0;
                int max = valInd.length;

                if(min != max){
                int ind = valInd[rnd.nextInt(min, max)];

                grid.setPoint(squares[g][ind],i);
                }
                else {
                    System.out.println("Error: min equals max");

                    breakPoint = true;

                    break;
                }
            }

            if (breakPoint) break;
        }
    }

    public static int[] validityToInt(boolean[] form) {
        int[] arr;
        int length = 0;

        for (int i = 0; i < form.length; i++) length += form[i] == true ? 1 : 0;

        arr = new int[length];

        length = 0;

        for (int i = 0; i < form.length; i++) {
            if (form[i]) {
                arr[length] = i;

                length++;
            }
        }

        return arr;
    }

    public static void updateValidity(boolean[] form, Grid grid, int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            form[i] = grid.getPoint(arr[i]) == 0 && isValid(grid, arr[i], value);
        }
    }

    public static void clearValidity(boolean[] form) {
        for (int i = 0; i < form.length; i++) form[i] = true;
    }

    public static void setValidityFalse(boolean[] form, int index) {
        form[index] = false;
    }

    public static boolean isValid_NoValue(Grid grid, int index) {
        if (grid == null || index < 0 || index >= grid.Length())
            return false;

        int[][] rows = grid.toRows();
        int[][] columns = grid.toColumns();
        int[][] squares = grid.toSquares();

        int value = grid.getPoint(index);

        int row_index = 0;
        int column_index = 0;
        int square_index = 0;

        int count = 0;

        for (int i = 0; i < rows.length; i++) {
            for (int g = 0; g < rows.length; g++) {
                if (rows[i][g] == index) {
                    row_index = i;

                    count++;
                }

                if (columns[i][g] == index) {
                    column_index = i;

                    count++;
                }

                if (squares[i][g] == index) {
                    square_index = i;

                    count++;
                }

                if (count == 3) break;
            }

            if (count == 3) break;
        }

        boolean flag = true;

        for (int i = 0; i < rows.length; i++) {
            int po = rows[row_index][i];
            int po2 = columns[column_index][i];
            int po3 = squares[square_index][i];

            int ind = grid.getPoint(po);
            int ind2 = grid.getPoint(po2);
            int ind3 = grid.getPoint(po3);

            if ((ind != 0 && ind == value && po != index) || (ind2 != 0 && ind2 == value && po2 != index) || (ind3 != 0 && ind3 == value && po3 != index)) {
                flag = false;

                break;
            }
        }

        return flag;
    }

    public static boolean isValid(Grid grid, int index, int value) {
        if (grid == null || index < 0 || index >= grid.Length())
            return false;

        int[] occur = grid.getCellReference(index);

        int row_index = occur[0];
        int column_index = occur[1];
        int square_index = occur[2];

        int[][] rows = grid.toRows();
        int[][] columns = grid.toColumns();
        int[][] squares = grid.toSquares();

        boolean flag = true;

        for (int i = 0; i < rows.length; i++) {
            int po = rows[row_index][i];
            int po2 = columns[column_index][i];
            int po3 = squares[square_index][i];

            int ind = grid.getPoint(po);
            int ind2 = grid.getPoint(po2);
            int ind3 = grid.getPoint(po3);

            if ((ind != 0 && ind == value && po != index) || (ind2 != 0 && ind2 == value && po2 != index) || (ind3 != 0 && ind3 == value && po3 != index)) {
                flag = false;

                break;
            }
        }

        return flag;
    }

    public static void print(Grid grid, int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            String str = "index " + i + ": ";

            for (int j = 0; j < arr[i].length; j++) {
                if (j < arr[i].length - 1) str += grid.getPoint(arr[i][j]) + ", ";
                else str += grid.getPoint(arr[i][j]);
            }

            System.out.println(str);
        }

        System.out.println("");
    }
}
