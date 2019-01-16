import java.util.LinkedList;
import java.util.Scanner;
import java.util.Queue;

public class kindsofpeople {

    private static int n_rows;
    private static int n_cols;
    private static char[][] m;
    private static int[][] sections;
    private static int n_query;
    private static int[][] query_array;
    private static int section;
    private static int[] dirX = {-1, 1, 0, 0};
    private static int[] dirY = {0, 0, -1, 1};
    private static Scanner sc;


    public static void main(String[] args) {


        sc = new Scanner(System.in);
        parse();
        section = 1;
        for (int i = 0; i < n_query; i++) {
            match(query_array[i]);
        }
    }


    private static void match(int[] query) {
        int x1 = query[0] - 1;
        int y1 = query[1] - 1;
        int x2 = query[2] - 1;
        int y2 = query[3] - 1;

        if (m[x1][y1] != m[x2][y2]) {
            System.out.println("neither");
        }
        else {
            if (sections[x1][y1] == 0 && sections[x2][y2] == 0) {
                flood(new Point2(x1, y1));
            }

            if ((sections[x1][y1] != sections[x2][y2])) {
                System.out.println("neither");
            } else {
                System.out.println(m[x1][y1] == '1' ? "decimal" : "binary");
            }
        }

    }

    private static void flood(Point2 start) {
        Queue<Point2> q = new LinkedList<>();

        q.add(start);

        char type = m[start.x][start.y];
        sections[start.x][start.y] = section;
        int x1, y1;

        while (!q.isEmpty()) {
            Point2 p = q.remove();
            x1 = p.x;
            y1 = p.y;
            for (int dir = 0; dir < 4; dir++) {
                int nx = x1 + dirX[dir];
                int ny = y1 + dirY[dir];
                if (legal_pos(nx, ny) && m[nx][ny] == type && sections[nx][ny] != section) {
                    q.add(new Point2(nx, ny));
                    sections[nx][ny] = section;
                }
            }
        }
        section++;
    }

    private static boolean legal_pos(int row, int col) {
        return (row >= 0 && row < n_rows && col >= 0 && col < n_cols);

    }

    private static void parse() {
        n_rows = sc.nextInt();
        n_cols = sc.nextInt();
        m = new char[n_rows][n_cols];
        sections = new int[n_rows][n_cols];

        for (int i = 0; i < n_rows; i++) {
            m[i] = sc.next().toCharArray();
        }
        n_query = sc.nextInt();
        query_array = new int[n_query][4];
        for (int i = 0; i < n_query; i++) {
            query_array[i][0] = sc.nextInt();
            query_array[i][1] = sc.nextInt();
            query_array[i][2] = sc.nextInt();
            query_array[i][3] = sc.nextInt();
        }
    }

}
class Point2 {

    int x, y;

    Point2(int a, int b) {
        x = a;
        y = b;
    }
}