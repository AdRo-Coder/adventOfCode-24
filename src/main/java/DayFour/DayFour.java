package DayFour;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayFour {

  static Pattern pattern = Pattern.compile("XMAS");

  public static void main(String[] args) {

    List<String> lines = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/DayFour/DayFour.csv"))) {
      String line;
      while ((line = br.readLine()) != null) {
        lines.add(line);
      }
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }

    System.out.println(lines.size());

    partOne(lines);
    partTwo(lines);

  }

  private static void partOne(List<String> lines) {
    int totalCount = 0;

    // horizontal
    for (String line : lines) {
      Matcher matcherHorizontal = pattern.matcher(line);
      while (matcherHorizontal.find()) {
        totalCount++;
      }
    }

    // vertical
    List<String> verticalLines = new ArrayList<>();
    for (int i = 0; i < lines.get(0).length(); i++) {
      StringBuilder vertical = new StringBuilder();
      for (String line : lines) {
        vertical.append(line.charAt(i));
      }
      verticalLines.add(vertical.toString());
    }
    for (String line : verticalLines) {
      Matcher matcherHorizontal = pattern.matcher(line);
      while (matcherHorizontal.find()) {
        totalCount++;
      }
    }

    // diagonally
    String[][] diagonally = new String[0][0];
    try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/DayFour/DayFour.csv"))) {
      List<String[]> dataList = new ArrayList<>();
      String line;
      while ((line = br.readLine()) != null) {
        String[] arr = new String[line.length()];
        for (int i = 0; i < line.length(); i++) {
          arr[i] = String.valueOf(line.charAt(i));
        }
        dataList.add(arr);
      }
      diagonally = dataList.toArray(new String[0][]);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    List<String> diagonal = getDiagonalValues(diagonally, lines.size() - 1);
    for (String line : diagonal) {
      Matcher matcherHorizontal = pattern.matcher(line);
      while (matcherHorizontal.find()) {
        totalCount++;
      }
    }
    for (String line : diagonal) {
      StringBuilder sb = new StringBuilder();
      sb.append(line);
      int index = diagonal.indexOf(line);
      diagonal.set(index, sb.reverse().toString());
    }
    for (String line : diagonal) {
      Matcher matcherHorizontal = pattern.matcher(line);
      while (matcherHorizontal.find()) {
        totalCount++;
      }
    }

    List<String> diagonalTwo = getDiagonalValuesTwo(diagonally, lines.size() - 1);
    for (String line : diagonalTwo) {
      Matcher matcherHorizontal = pattern.matcher(line);
      while (matcherHorizontal.find()) {
        totalCount++;
      }
    }
    for (String line : diagonalTwo) {
      StringBuilder sb = new StringBuilder();
      sb.append(line);
      int index = diagonalTwo.indexOf(line);
      diagonalTwo.set(index, sb.reverse().toString());
    }
    for (String line : diagonalTwo) {
      Matcher matcherHorizontal = pattern.matcher(line);
      while (matcherHorizontal.find()) {
        totalCount++;
      }
    }


    // 3024 to high
    // 2025 false

    System.out.println(totalCount);
  }

  private static List<String> getDiagonalValuesTwo(String[][] arr, int size) {
    int i;
    int j;
    int k;
    List<String> diagonalList = new ArrayList<>();
    for (k = size; k >= 0; k--) {
      i = k;
      j = 0;
      StringBuilder sb = new StringBuilder();
      while (i <= size) {
        sb.append(arr[i][j]);
        i = i + 1;
        j = j + 1;
      }
      diagonalList.add(sb.toString());
    }

    for (k = 1; k <= size; k++) {
      i = 0;
      j = k;
      StringBuilder sb = new StringBuilder();
      while (j <= size) {
        sb.append(arr[i][j]);
        i = i + 1;
        j = j + 1;
      }
      diagonalList.add(sb.toString());
    }
    return diagonalList;
  }

  private static List<String> getDiagonalValues(String[][] arr, int size) {
    int i;
    int j;
    int k;
    List<String> diagonalList = new ArrayList<>();
    for (k = 0; k <= size; k++) {
      i = k;
      j = 0;
      StringBuilder sb = new StringBuilder();
      while (i >= 0) {
        sb.append(arr[j][i]);
        i = i - 1;
        j = j + 1;
      }
      diagonalList.add(sb.toString());
    }

    for (k = 1; k <= size; k++) {
      i = size;
      j = k;
      StringBuilder sb = new StringBuilder();
      while (j <= size) {
        sb.append(arr[i][j]);
        i = i - 1;
        j = j + 1;
      }
      diagonalList.add(sb.toString());
    }
    return diagonalList;
  }

  private static void partTwo(List<String> lines) {
  }

}
