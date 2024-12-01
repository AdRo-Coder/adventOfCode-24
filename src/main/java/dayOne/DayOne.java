package dayOne;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DayOne {


    public static void main(String[] args) {

        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/dayOne/DayOne.csv"))) {
            String line;
            String[] values = new String[0];
            while ((line = br.readLine()) != null) {
                values = line.split(";");
            }

            int i = 0;
            for (String value : values) {
                if (i == 0) {
                    try {
                        leftList.add(Integer.parseInt(value));
                    } catch (NumberFormatException e) {
                        System.out.println(e.getMessage());
                        leftList.add(88159);
                    }
                    i++;
                } else {
                    rightList.add(Integer.parseInt(value));
                    i--;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        partOne(leftList, rightList);
        partTwo(leftList, rightList);
    }

    private static void partTwo(List<Integer> leftList, List<Integer> rightList) {
        int sum = 0;
        for (Integer i : leftList) {
            int timesFound = (int) rightList.stream().filter(y -> Objects.equals(y, i)).count();
            sum += i * timesFound;
        }
        System.out.println(sum);
    }

    private static void partOne(List<Integer> leftList, List<Integer> rightList) {
        Collections.sort(leftList);
        Collections.sort(rightList);

        int sum = 0;
        for (int i = 0; i < leftList.size(); i++) {
            if (leftList.get(i) > rightList.get(i)) {
                sum += leftList.get(i)- rightList.get(i);
            } else {
                sum += rightList.get(i) - leftList.get(i);
            }
        }

        System.out.println(sum);
    }
}

