package DayTwelve;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DayTwelve {


    public static void main(String[] args) {
        partOne(readFile("DayTwelveTestInput.csv"));
//        partOne(readFile("DayTwelve.csv"));
    }

    private static void partOne(String[][] matrix) {
        System.out.println(matrix.length);
        System.out.println(matrix[0].length);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {

            }
        }

    }

    private static void findAllConnections(String[][] matrix, String s, int posI, int posJ) {
        String currentField = matrix[posI][posJ];
        while (currentField.equals(s)) {

        }
    }

    private static String[][] readFile(String fileName) {
        String[][] matrix;
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/DayTwelve/" + fileName))) {
            List<String[]> dataList = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = new String[line.length()];
                for (int i = 0; i < line.length(); i++) {
                    arr[i] = String.valueOf(line.charAt(i));
                }
                dataList.add(arr);
            }
            matrix = dataList.toArray(new String[0][]);
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
        return matrix;
    }
}
