import java.awt.*;
import java.util.Scanner;
class P2048 {

    static int n_row = 4;
    static int n_col = 4;
    static int[][] m = new int [4][4];
    static boolean[][] merged_array = new boolean[4][4];

    static int dir;
    static int[] dirX = {-1, 0, 1, 0};
    static int[] dirY = {0, -1, 0, 1};
    static int[] startRow = {0, 0, 0, 3};
    static int[] startCol = {0, 0, 3, 0};
    static int[] endRow = {4, 4, 4, -1};
    static int[] endCol = {4, 4, -1, 4};

    static Scanner sc;

    public static void main(String[] args) {

        sc = new Scanner(System.in);
        parse();
        sc.close();
        move();
        print_board();

    }

    public static void move() {

        int stepRow = endRow[dir] - startRow[dir] > 0 ? 1 : -1;
        int stepCol = endCol[dir] - startCol[dir] > 0 ? 1 : -1;
        for (int row = startRow[dir]; row != endRow[dir]; row = row + stepRow) {
            for (int col = startCol[dir]; col != endCol[dir]; col = col + stepCol) {
                int cRow = row;
                int cCol = col;
                int nRow = cRow + dirY[dir];
                int nCol = cCol + dirX[dir];
                boolean merged = false;
                while (legal_move(nRow, nCol) && m[cRow][cCol] != 0 && (m[nRow][nCol] == 0 || (!merged && m[nRow][nCol] == m[cRow][cCol]))) {

                    if (m[nRow][nCol] == m[cRow][cCol]) {
                        m[nRow][nCol] = m[nRow][nCol] * 2;
                        m[cRow][cCol] = 0;
                        merged = true;
                    }
                    else if (m[nRow][nCol] == 0) {
                        m[nRow][nCol] = m[cRow][cCol];
                        m[cRow][cCol] = 0;
                    }
                    cRow = nRow;
                    cCol = nCol;
                    nRow = cRow + dirY[dir];
                    nCol = cCol + dirX[dir];

                }
                if (merged) {
                    merged_array[cRow][cCol] = true;
                }
            }
        }
    }


    public static boolean legal_move(int row, int col) {
        if (!(row >= 0 && row < n_row && col >= 0 && col < n_col)) {
            return false;
        }
        else {
            return !merged_array[row][col];
        }
    }

    public static void print_board() {
        int row, col;
        for (row = 0; row < n_row; row++) {
            for (col = 0; col < n_col - 1; col++) {
                System.out.print(m[row][col] + " ");
            }
            System.out.println(m[row][col]);
        }
    }

    public static void parse() {
        for (int row = 0; row < n_row; row++) {
            for (int col = 0; col < n_col; col++) {
                m[row][col] = sc.nextInt();
            }
        }
        dir = sc.nextInt();
    }
}
