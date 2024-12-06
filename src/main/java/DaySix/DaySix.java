package DaySix;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DaySix {

  public static void main(String[] args) {
    String matrix[][] = readFile();
    int startingPositionI = 0;
    int startingPositionJ = 0;
    int countFields = 0;

    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        if (Objects.equals(matrix[i][j], "^")) {
          startingPositionI = i;
          startingPositionJ = j;
        }
      }
    }
    System.out.println(startingPositionI + " " + startingPositionJ);

    matrix[startingPositionI][startingPositionJ] = "X";

    int posI = startingPositionI;
    int posJ = startingPositionJ;
    String facingDirection = "north";
    while (
        posI < matrix.length - 1 && posJ < matrix[0].length - 1
    ) {
      boolean changedDirection = false;
      switch (facingDirection) {
      case "north": {
        if (!matrix[posI - 1][posJ].equals("#")) {
          posI--;
        } else {
          facingDirection = "east";
          changedDirection = true;
        }
        break;
      }
      case "east": {
        if (!matrix[posI][posJ + 1].equals("#")) {
          posJ++;
        } else {
          facingDirection = "south";
          changedDirection = true;
        }
        break;
      }
      case "south": {
        if (!matrix[posI + 1][posJ].equals("#")) {
          posI++;
        } else {
          facingDirection = "west";
          changedDirection = true;
        }
        break;
      }
      case "west": {
        if (!matrix[posI][posJ - 1].equals("#")) {
          posJ--;
        } else {
          changedDirection = true;
          facingDirection = "north";
        }
        break;
      }
      }
      System.out.println(posI + " " + posJ + " " + facingDirection);
      String currentField = matrix[posI][posJ];
      if (!changedDirection && !currentField.equals("X")) {
        countFields++;
      }
      matrix[posI][posJ] = "X";
    }

    // to high 5641
    // to high 5479
    // to low 4963
    System.out.println(countFields);
    writeMatrix(matrix);
  }

  private static String[][] readFile() {
    String[][] matrix;
    try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/DaySix/DaySix.csv"))) {
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
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return matrix;
  }

  private static void writeMatrix(String[][] matrix) {
    try {
      BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/java/DaySix/DaySixResult.csv"));

      for (int i = 0; i < matrix.length; i++) {
        for (int j = 0; j < matrix[i].length; j++) {
          bw.write(matrix[i][j]);
        }
        bw.newLine();
      }
      bw.flush();
    } catch (IOException e) {
    }
  }

}
