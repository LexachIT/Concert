import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<int[]> places = readPlacesFromFile("Text.txt");
        if (places.isEmpty()) {
            System.out.println("Номеров мест не найдено.");
            return;
        }
        sortPlaces(places);
        findAndPrintSuitableSeats(places);
    }

    // Метод для чтения мест из файла
    private static List<int[]> readPlacesFromFile(String filename) {
        List<int[]> places = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(filename))) {
            int countRow = Integer.parseInt(bf.readLine());
            for (int i = 0; i < countRow; i++) {
                String[] temp = bf.readLine().split(" ");
                places.add(new int[]{Integer.parseInt(temp[0]), Integer.parseInt(temp[1])});
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
        return places;
    }

    // Метод для сортировки мест
    private static void sortPlaces(List<int[]> places) {
        places.sort(Comparator.comparingInt((int[] a) -> a[0])
                .thenComparing((int[] a) -> a[1], Comparator.reverseOrder()));
    }

    // Метод для поиска и печати подходящих мест
    private static void findAndPrintSuitableSeats(List<int[]> places) {
        List<String> suitableSeats = new ArrayList<>();
        for (int i = 1; i < places.size(); i++) {
            // Проверяем, если места идут подряд с пропуском
            if (places.get(i)[0] == places.get(i - 1)[0] && places.get(i)[1] - places.get(i - 1)[1] == -3) {
                int maxRow = places.get(i)[0];
                int minPlace = places.get(i)[1] + 1; // Следующее место
                suitableSeats.add(maxRow + " " + minPlace);
            }
        }

        // Выводим результаты
        if (suitableSeats.isEmpty()) {
            System.out.println("Подходящие места не найдены.");
        } else {
            System.out.println("Подходящие места:");
            for (String seat : suitableSeats) {
                System.out.println(seat);
            }
        }
    }
}


