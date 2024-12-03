package DayTwo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DayTwo {

    public static void main(String[] args) {

        List<int[]> values = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/dayTwo/DayTwo.csv"))) {
            String line;
            String[] lineValues;
            while ((line = br.readLine()) != null) {
                lineValues = line.split(";");
                int[] valuesAsInt = new int[lineValues.length];
                for (int i = 0; i < lineValues.length; i++) {
                    valuesAsInt[i] = Integer.parseInt(lineValues[i]);
                }
                values.add(valuesAsInt);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(values.size());
        partOne(values);
        partTwo(values);
    }

    private static void partTwo(List<int[]> values) {
        int sum = 0;
        for (int[] value : values) {
            List<String> list = checkValues(value);
            Set<String> set = new HashSet<>(list);
            if (set.size() == 1) {
                sum++;
            } else {
                boolean safe = false;
                for (int i = 0; i < value.length; i++) {
                    List<Integer> copy = createCopy(value);
                    copy.remove(i);

                    int[] checkAgain = convToArray(copy);
                    HashSet<String> setToCheck = new HashSet<>(checkValues(checkAgain));
                    if (setToCheck.size() == 1 && (setToCheck.contains("Increase") || setToCheck.contains("Decrease"))) {
                        safe = true;
                        break;
                    }
                }
                if (safe) {
                    sum++;
                }

                //  sum = getSum(value, list, set, sum);
            }

        }
        System.out.println(sum);
    }

    private static int[] convToArray(List<Integer> copy) {
        int[] checkAgain = new int[copy.size()];
        for (int i = 0; i < copy.size(); i++) {
            checkAgain[i] = copy.get(i);
        }
        return checkAgain;
    }

    private static List<Integer> createCopy(int[] value) {
        List<Integer> copy = new ArrayList<>();
        for (int i : value) {
            copy.add(i);
        }
        return copy;
    }

    private static List<String> checkValues(int[] value) {
        List<Integer> allowedIncreases = List.of(1, 2, 3);
        List<Integer> allowedDecreases = List.of(-1, -2, -3);
        List<String> set = new ArrayList<>();
        for (int i = 0; i < value.length; i++) {
            if (i + 1 < value.length) {
                int cal = value[i] - value[i + 1];
                if (allowedIncreases.contains(cal)) {
                    set.add("Decrease");
                } else if (allowedDecreases.contains(cal)) {
                    set.add("Increase");
                } else if (cal == 0) {
                    set.add("Zero");
                } else if (cal > 3) {
                    set.add("To Big");
                } else {
                    set.add("To Small");
                }
            }
        }
        return set;
    }

    private static void partOne(List<int[]> values) {
        List<Integer> allowedIncreases = List.of(1, 2, 3);
        List<Integer> allowedDecreases = List.of(-1, -2, -3);

        int sum = 0;
        for (int[] value : values) {
            Set<String> set = new HashSet<>();
            for (int i = 0; i < value.length; i++) {
                if (i + 1 < value.length) {
                    int cal = value[i] - value[i + 1];
                    if (allowedIncreases.contains(cal)) {
                        set.add("Increase");
                    } else if (allowedDecreases.contains(cal)) {
                        set.add("Decrease");
                    } else {
                        set.add("Nothing");
                    }
                }


                if (i == value.length - 1) {
                    if (set.size() == 1) {
                        sum++;
                    }
                }
            }
        }
        System.out.println(sum);
    }

}
