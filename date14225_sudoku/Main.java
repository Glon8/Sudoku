package OnDate2.date14225_sudoku;

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

        System.out.println("The index 60 valid is " + isValid(grid, rows, cols, squares, 60));

        grid.setPoint(61, 58);

        System.out.println("The index 60 valid is " + isValid(grid, rows, cols, squares, 60));

        System.out.println(grid);
    }

    public static boolean isValid(Grid grid, int[][] rows, int[][] columns, int[][] squares, int index) {
        if (rows == null || squares == null || columns == null || index < 0 || index >= grid.Length())
            return false;

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
            int ind = grid.getPoint(rows[row_index][i]);

            if (ind != 0)
                for (int g = 0; g < rows.length; g++) {
                    if(rows[row_index][i] != columns[column_index][g]) flag = ind != grid.getPoint(columns[column_index][g]);

                    if (!flag) break;
                }

            if (!flag) break;
        }

        if (flag)
            for (int i = 0; i < rows.length; i++) {
                int ind = grid.getPoint(columns[column_index][i]);

                if (ind != 0)
                    for (int g = 0; g < rows.length; g++) {
                        if(columns[column_index][i] != squares[square_index][g]) flag = ind != grid.getPoint(squares[square_index][g]);

                        if (!flag) break;
                    }

                if (!flag) break;
            }

        if (flag)
            for (int i = 0; i < rows.length; i++) {
                int ind = grid.getPoint(rows[row_index][i]);

                if (ind != 0)
                    for (int g = 0; g < rows.length; g++) {
                        if(rows[row_index][i] != squares[square_index][g]) flag = ind != grid.getPoint(squares[square_index][g]);

                        if (!flag) break;
                    }

                if (!flag) break;
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
