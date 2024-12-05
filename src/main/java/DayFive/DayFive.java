package DayFive;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DayFive {

  public static void main(String[] args) {

    List<int[]> input = readInput("src/main/java/DayFive/DayFiveInput.csv", ",");
    Set<String> rules = readRules("src/main/java/DayFive/DayFiveRules.csv", "|");

    List<int[]> rightOrderList = partOne(input, rules);
    partTwo(input, rules, rightOrderList);

  }

  private static void partTwo(List<int[]> input, Set<String> rules, List<int[]> rightOrderList) {
    int count = 0;
    List<int[]> falseOrderList = new ArrayList<>(input);
    input.forEach(o -> {
      if (rightOrderList.contains(o)) {
        falseOrderList.remove(o);
      }
    });

    for (int[] line : falseOrderList) {
      boolean rightOrder = true;
      while (rightOrder) {
        int i = 0;
        while (i < line.length - 1) {
          int numberOne = line[i];
          int numberTwo = line[i + 1];
          long present = rules.parallelStream().filter(rule -> rule.startsWith(String.valueOf(numberOne)) && rule.endsWith(String.valueOf(numberTwo))).count();

          if (present != 1) {
            line[i] = numberTwo;
            line[i + 1] = numberOne;
          } else {
            i++;
          }
          if (i == line.length - 2) {
            count += line[line.length / 2];
            rightOrder = false;
            i++;
          }
        }
      }
    }

    System.out.println(falseOrderList.size());

    // 5902 to high

    System.out.println(count);
  }

  private static List<int[]> partOne(List<int[]> input, Set<String> rules) {
    int count = 0;
    int rightOrderLines = 0;
    List<int[]> rightOrderList = new ArrayList<>();

    for (int[] line : input) {
      boolean rightOrder = true;
      while (rightOrder) {
        for (int i = 0; i < line.length - 1; i++) {
          int numberOne = line[i];
          int numberTwo = line[i + 1];
          long present = rules.parallelStream().filter(rule -> rule.startsWith(String.valueOf(numberOne)) && rule.endsWith(String.valueOf(numberTwo))).count();

          if (present != 1) {
            rightOrder = false;
            break;
          }

          if (i == line.length - 2) {
            count += line[line.length / 2];
            rightOrderLines++;
            rightOrderList.add(line);
            rightOrder = false;
          }
        }
      }
    }
    System.out.println(rightOrderLines);
    System.out.println(count);
    return rightOrderList;
  }

  private static Set<String> readRules(String filename, String regEx) {
    Set<String> input = new HashSet<>();

    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = br.readLine()) != null) {
        input.add(line);
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }

    return input;
  }

  private static List<int[]> readInput(String filename, String regEx) {
    List<int[]> input = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] arr = line.split(regEx);
        int[] intArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
          intArr[i] = Integer.parseInt(arr[i]);
        }
        input.add(intArr);
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }

    return input;
  }

}
