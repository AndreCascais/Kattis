import java.util.ArrayList;
import java.util.Scanner;

public class weakvertices {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int size = s.nextInt();
        while (size != -1) {
            int[][] m = new int[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    m[i][j] = s.nextInt();
                }
            }

            ArrayList<Integer> weak_vertex_list = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                boolean part_of_triangle = false;
                for (int j = 0; j < size; j++) {
                    if (m[i][j] == 1 && i != j) {
                        for (int k = j; k < size; k++) {
                            if (m[j][k] == 1 && m[i][k] == 1 && i != k && j != k) {
                                part_of_triangle = true;
                                break;
                            }
                        }
                    }
                }
                if (!part_of_triangle) {
                    weak_vertex_list.add(i);
                }
            }


            StringBuilder output = new StringBuilder();
            for (int v : weak_vertex_list) {
                output.append(v).append(" ");
            }
            System.out.println(output);

            size = s.nextInt();
        }
    }
}