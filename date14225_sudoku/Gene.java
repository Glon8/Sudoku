package OnDate2.date14225_sudoku;

import java.util.Random;

public class Gene {
    Grid grid;

    public Gene(Grid grid) {
        this.grid = grid;
    }

    public void generate() {
        this.grid.clearGrid();

        for (int i = 1; i <= 9; i++) {
            int[] readyCells = readySquaresCells(i);

            for (int g = 0; g < readyCells.length; g++) if (readyCells[g] != -1) this.grid.setPoint(readyCells[g], i);
        }
    }

    //< returns valid cells for each square, which are suit to place a digit in, which do not intercept with each other on the crossings(row, column, block(square)),
    //   which do not blocking squares from receiving the digit and prioritizing a lonely empty celled squares.
    public int[] readySquaresCells(int value) {
        if (value == 0) return null; //< won't check if value is zero, science it means empty cell.

        int[][] squares = this.grid.toSquares(); //< splitting grid to squares. (sorting indexes by squares)

        int length = squares.length;

        boolean[] form = new boolean[length]; //< initializing boolean form for square. (ll be used for validation in the future)

        int[][] validIndexes = new int[length][length]; //< creating array to contain all the suitable cells.

        // first check
        for (int i = 0; i < length; i++) {
            updateBooleanForm(form, squares[i], value); //< running first check on squares, filling by false, boolean form with intercepting crossings. (row, column, block(square))

            validIndexes[i] = booleanFormToInt(form); //< converting boolean form into "valid" array of indexes, per square. (*Updating valid indexes)
        }

        int[] squaresIndexes; //< initializing array which ll return the cells for input.

        // second check
        squaresIndexes = validInterception(grid, validIndexes, value); //< running second check on squares, returning the indexes which 'checked deeply' and have no interceptions and leave space for future cells.

        return squaresIndexes;
    }

    public int[] validInterception(Grid grid, int[][] arr, int value) {
        if (arr == null || grid == null) {
            System.out.println("Error 0: 'validInterception'.");

            return null;
        }

        int arrLength = arr.length;

        int[] result = new int[arrLength];

        for(int i = 0; i < arrLength; i++){
            //================================================== point selection
            Random rnd = new Random();

            int[] dif = difference(0,arr[i].length);

            int ind = dif != null? rnd.nextInt(dif[0], dif[1]) : 0;

            result[i] = arr[i][ind];

            //================================================== position check
            int[] pos = grid.getCellReference(result[i]);

            int[][] copy = new int[arrLength][1];

            for(int j = 0; j < arrLength; j++) copy[j] = copyArray(arr[j]);



            //==================================================
        }

        return result;
    }

    //< creating copy of array.
    public int[] copyArray(int[] arr){
        if(arr == null) return null;

        int[] copy = new int[arr.length];

            for (int i = 0; i < arr.length; i++) {
                copy[i] = arr[i];
            }

            return copy;
    }

    //< removing index from array and updating the given array.
    public void removeIndexFromArray(int[] arr, int index){
        if(!(arr.length == 0 || arr == null || index >= arr.length || index < 0)){
            int[] temp = new int[arr.length - 1];

            int count = 0;

            for(int i = 0; i < arr.length; i++){
                if(i != index) {
                    temp[count] = arr[i];

                    count++;
                }
            }

            arr = temp;
        }
    }

    //< checks validation for min and max. (if all alright returns them as array)
    public int[] difference(int min, int max){
        if(min >= max) return null;
        else{
            int[] arr = {min, max};

            return arr;
        }
    }

    //< converts boolean array into integer array. (only empty cells are returned in int array)
    public int[] booleanFormToInt(boolean[] form) {
        int[] arr;
        int length = 0;

        for (int i = 0; i < form.length; i++) length += form[i] ? 1 : 0;

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

    //< checks given int array, how much empty cells (zeros) it have and if they are not intercept with each other at the crossings(row, column, block(square)),
    //   and with it fills the boolean array.
    public void updateBooleanForm(boolean[] form, int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            form[i] = grid.getPoint(arr[i]) == 0 && isValid(arr[i], value);
        }
    }

    //< sets all indexes in boolean array to true
    public void clearBooleanForm(boolean[] form) {
        for (int i = 0; i < form.length; i++) form[i] = true;
    }

    //< sets exact index in boolean array to false
    public void setBooleanFormCellToFalse(boolean[] form, int index) {
        form[index] = false;
    }

    //< this validation checks if the digit which ALREADY exists in a given cell, isn't duplicated in all its crossings. (row, column, block(square))
    public boolean isValid(int index) {
        if (this.grid == null || index < 0 || index >= this.grid.Length())
            return false;

        int[][] rows = this.grid.toRows();
        int[][] columns = this.grid.toColumns();
        int[][] squares = this.grid.toSquares();

        int value = this.grid.getPoint(index);

        int[] occur = this.grid.getCellReference(index);

        int row_index = occur[0];
        int column_index = occur[1];
        int square_index = occur[2];

        boolean flag = true;

        for (int i = 0; i < rows.length; i++) {
            int po = rows[row_index][i];
            int po2 = columns[column_index][i];
            int po3 = squares[square_index][i];

            int ind = this.grid.getPoint(po);
            int ind2 = this.grid.getPoint(po2);
            int ind3 = this.grid.getPoint(po3);

            if ((ind != 0 && ind == value && po != index) || (ind2 != 0 && ind2 == value && po2 != index) || (ind3 != 0 && ind3 == value && po3 != index)) {
                flag = false;

                break;
            }
        }

        return flag;
    }

    //< this validation checks if the given digit which NOT exists yet in a given cell, might be duplicated in all its crossings. (row, column, block(square))
    public boolean isValid(int index, int value) {
        if (this.grid == null || index < 0 || index >= this.grid.Length())
            return false;

        int[] occur = this.grid.getCellReference(index);

        int row_index = occur[0];
        int column_index = occur[1];
        int square_index = occur[2];

        int[][] rows = this.grid.toRows();
        int[][] columns = this.grid.toColumns();
        int[][] squares = this.grid.toSquares();

        boolean flag = true;

        for (int i = 0; i < rows.length; i++) {
            int po = rows[row_index][i];
            int po2 = columns[column_index][i];
            int po3 = squares[square_index][i];

            int ind = this.grid.getPoint(po);
            int ind2 = this.grid.getPoint(po2);
            int ind3 = this.grid.getPoint(po3);

            if ((ind != 0 && ind == value && po != index) || (ind2 != 0 && ind2 == value && po2 != index) || (ind3 != 0 && ind3 == value && po3 != index)) {
                flag = false;

                break;
            }
        }

        return flag;
    }

    //< prints complex array by rows
    public void print(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            String str = "index " + i + ": ";

            for (int j = 0; j < arr[i].length; j++) {
                if (j < arr[i].length - 1) str += this.grid.getPoint(arr[i][j]) + ", ";
                else str += this.grid.getPoint(arr[i][j]);
            }

            System.out.println(str);
        }

        System.out.println("");
    }
}

/*
!!!from squares for loop generation!!!

Random rnd = new Random();

                updateBooleanForm(validityForm, squares[g], i);

                int[] valInd = booleanFormToInt(validityForm);

                //System.out.println("number: " + i + "   square: " + g);
                //System.out.println(this.grid);

                int min = 0;
                int max = valInd.length;

                if(min != max){
                    int ind = valInd[rnd.nextInt(min, max)];

                    this.grid.setPoint(squares[g][ind],i);
                }
                else {
                    System.out.println("Error: min equals max");

                    breakPoint = true;

                    break;
                }
 */
