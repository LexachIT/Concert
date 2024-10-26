import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[][] seats = new int[n][2];

        for (int i = 0; i < n; i++) {
            seats[i][0] = scanner.nextInt();
            seats[i][1] = scanner.nextInt();
        }

        Arrays.sort(seats, Comparator.comparingInt(a -> a[0]));

        int maxRow = 0;
        int minSeat = 0;

        for (int i = 0; i < n - 1; i++) {
            if (seats[i][0] == seats[i + 1][0] && seats[i + 1][1] - seats[i][1] == 1) {
                if (seats[i][0] > maxRow) {
                    maxRow = seats[i][0];
                    minSeat = seats[i][1];
                }
            }
        }

        System.out.println(maxRow + " " + (minSeat + 1));
    }
}
