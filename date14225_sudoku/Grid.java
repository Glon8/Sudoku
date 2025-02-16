package OnDate2.date14225_sudoku;

public class Grid {
    private final int sqr_root;

    private final int arr_length;

    private int[] grid;

    public Grid(int arr_length, boolean test) {
        this.arr_length = arr_length;

        this.sqr_root = (int) Math.sqrt(arr_length);

        this.grid = new int[arr_length];

        if (!test)
            for (int i : this.grid) {
                i = 0;
            }
        else
            for (int i = 0; i < arr_length; i++) {
                this.grid[i] = 1 + i;
            }
    }

    public Grid(int sqr_root) {
        if (sqr_root % 3 != 0) {
            this.sqr_root = 0;

            this.arr_length = 0;

            System.out.println("Grid Error Definition: Invalid root - root must be dividable by 3!");
        } else {
            this.sqr_root = sqr_root;

            this.arr_length = sqr_root * sqr_root;

            this.grid = new int[arr_length];

            for (int i : this.grid) {
                i = 0;
            }
        }
    }

    public int Length(){
        return arr_length;
    }

    public int[] toZeroList() {
        int count  = 0;

        for(int i: grid)
            if(i == 0) count++;

        int[] arr = new int[count];

        count = 0;

        for(int i = 0; i < arr_length; i++){
            if(grid[i] == 0) {
                arr[count] = i;

                count++;
            }
        }

        return arr;
    }

    public String queryToString(int[] query, boolean detailed) {
        if (query == null || query.length == 0) return "";

        String str = "[ ";

        if (detailed)
            for (int i : query) str += this.grid[i] + "(i." + i + ") ";
        else
            for (int i : query) str += this.grid[i] + " ";

        str += "]";

        return str;
    }

    public int[][] toSquares() {
        int sqrSide = this.sqr_root / 3;

        int[][] squares = new int[this.sqr_root][this.sqr_root];

        for (int i = 0; i < this.sqr_root; i++) {
            if(i%3 == 0 || i == 0) squares[i][0] = i * sqr_root;
            else squares[i][0] = squares[i-1][0] + sqrSide;

            squares[i][1] = 1 + squares[i][0];
            squares[i][2] = 1 + squares[i][1];
            squares[i][3] = this.sqr_root + squares[i][0];
            squares[i][4] = this.sqr_root + squares[i][1];
            squares[i][5] = this.sqr_root + squares[i][2];
            squares[i][6] = this.sqr_root + squares[i][3];
            squares[i][7] = this.sqr_root + squares[i][4];
            squares[i][8] = this.sqr_root + squares[i][5];
        }

        return squares;
    }

    public int[][] toColumns() {
        int[][] columns = new int[this.sqr_root][this.sqr_root];

        for (int i = 0; i < this.sqr_root; i++) {
            for (int j = 0; j < this.sqr_root; j++) {
                int index = i + (j * this.sqr_root);

                columns[i][j] = index;
            }
        }

        return columns;
    }

    public int[][] toRows() {
        int[][] rows = new int[this.sqr_root][this.sqr_root];

        for (int i = 0; i < this.sqr_root; i++) {
            for (int j = 0; j < this.sqr_root; j++) {
                int index = i * sqr_root + j;

                rows[i][j] = index;
            }
        }

        return rows;
    }

    public void clearGrid() {
        for (int i : this.grid) {
            i = 0;
        }
    }

    public int getPoint(int index) {
        if (index > this.arr_length - 1 || index < 0) return -1;
        else return this.grid[index];
    }

    public boolean setPoint(int index, int value) {
        if (index > this.arr_length - 1 || index < 0) return false;
        else {
            this.grid[index] = value;

            return true;
        }
    }

    @Override
    public String toString() {
        String str = "[\r\n";

        for (int i = 0; i < this.arr_length; i++) {
            str += grid[i] + " ";
            if ((1 + i) % sqr_root == 0) str += "\r\n";
        }

        str += "]\r\n";

        return str;
    }
}
