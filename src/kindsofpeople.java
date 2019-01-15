import java.util.Scanner;
import java.util.Arrays;

public class kindsofpeople {

    private static int n_rows;
    private static int n_cols;
    private static char[][] m;
    private static int n_query;
    private static int[][] query_array;
    private static Scanner sc;
    private static int[][] visited;


    public static void main(String[] args) {
        sc = new Scanner(System.in);
        parse();
        for (int i = 0; i < n_query; i++) {
            match(query_array[i]);
            for (int[] row: visited) {
                Arrays.fill(row, 0);
            }

        }
    }


    private static void match(int[] query) {
        int x1 = query[0];
        int y1 = query[1];
        int x2 = query[2];
        int y2 = query[3];

        if (m[x1][y1] != m[x2][y2]) {
            System.out.println("neither");
            return;
        }
        else {
            int type = m[x1][y1];
            if (path(x1, y1, x2, y2)) {
                System.out.println(type == '1' ? "decimal" : "binary");
            }
            else {
                System.out.println("neither");
            }
        }

    }

    private static boolean path(int x1, int y1, int x2, int y2) {
        if (x1 == x2 && y1 == y2) {
            return true;
        }
        if(visited[x1][y1] == 1) {
            return false;
        }
        char type = m [x1][y1];



        boolean res = false;
        visited[x1][y1] = 1;
        if (m[(x1 + 1)][y1] == type) {
            res = res || path((x1 + 1), y1, x2, y2);
        }
        if (m[(x1 - 1)][y1] == type) {
            res = res || path((x1 - 1), y1, x2, y2);
        }
        if (m[x1][(y1 + 1)] == type) {
            res = res || path(x1, (y1 + 1), x2, y2);
        }
        if (m[x1][(y1 - 1)] == type) {
            res = res || path(x1, (y1 - 1), x2, y2);
        }
        return res;

    }
    private static void parse() {
        n_rows = sc.nextInt();
        n_cols = sc.nextInt();
        m = new char[n_rows + 2][n_cols + 2];
        visited = new int[n_rows + 2][n_cols + 2];

        for (int i = 0; i < n_cols + 2; i++) {
            m[0][i] = '2';
            m[n_rows + 1][i] = '2';
        }
        for (int i = 1; i < n_rows + 1; i ++) {
            m[i][0] = 2;
            m[i][n_cols + 1] = '2';
            String line = sc.next();
            for (int j = 1; j < n_cols + 1; j++){
                m[i][j] = line.charAt(j - 1);
            }
        }
        n_query = sc.nextInt();
        query_array = new int[n_query ][4];
        for (int i = 0; i < n_query; i++) {
            query_array[i][0] = sc.nextInt();
            query_array[i][1] = sc.nextInt();
            query_array[i][2] = sc.nextInt();
            query_array[i][3] = sc.nextInt();
        }
  }

}
