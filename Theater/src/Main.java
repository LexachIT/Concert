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
        findAndPrintSuitableSeat(places);
    }

    private static List<int[]> readPlacesFromFile(String filename) {
        List<int[]> places = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(filename))) {
            int countRow = Integer.parseInt(bf.readLine()); // считываем первую строку, содержащую количество мест

            for (int i = 0; i < countRow; i++) {
                String[] temp = bf.readLine().split(" "); // чтение ряда и места в каждой строке
                places.add(new int[]{Integer.parseInt(temp[0]), Integer.parseInt(temp[1])});
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
        return places;
    }

    private static void sortPlaces(List<int[]> places) {
        // Сортировка всех пар сначала по номеру ряда по возрастанию, а затем по номеру места по убыванию
        places.sort(Comparator.comparingInt((int[] a) -> a[0])
                .thenComparing((int[] a) -> a[1], Comparator.reverseOrder()));
    }

    private static void findAndPrintSuitableSeat(List<int[]> places) {
        int maxRow = -1, minPlace = -1;

        for (int i = 1; i < places.size(); i++) {
            // Ищем подходящее место
            if (places.get(i)[0] == places.get(i - 1)[0] && places.get(i)[1] - places.get(i - 1)[1] == -3) {
                maxRow = places.get(i)[0];
                minPlace = places.get(i)[1] + 1; // следующее свободное место
            }
        }

        if (maxRow == -1 || minPlace == -1) {
            System.out.println("No suitable seat found.");
        } else {
            System.out.println(maxRow + " " + minPlace);
        }
    }
}

