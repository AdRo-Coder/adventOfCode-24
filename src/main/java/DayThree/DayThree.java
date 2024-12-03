package DayThree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayThree {

    public static void main(String[] args) {
        List<String> fileInputAsList = readFile();
        partOne(fileInputAsList);
        partTwo(fileInputAsList);
    }

    private static void partOne(List<String> fileInputAsList) {
        Pattern pattern = Pattern.compile("mul\\([0-9]*,[0-9]*\\)");
        int sum = 0;
        for (String s : fileInputAsList) {
            Matcher matcher = pattern.matcher(s);
            sum += calculateSum(matcher);
        }
        System.out.println(sum);
    }

    private static void partTwo(List<String> fileInputAsList) {
        StringBuilder completeFileSB = new StringBuilder();
        int sum = 0;
        for (String line : fileInputAsList) {
            completeFileSB.append(line);
        }
        String completeFile = completeFileSB.toString();

        Pattern dontPat = Pattern.compile("don't\\(\\)");
        Pattern mulPat = Pattern.compile("mul\\([0-9]*,[0-9]*\\)");

        int firstDont = completeFile.indexOf("don't");
        String firstAllowedMuls = completeFile.substring(0, firstDont);

        Matcher firstMatcher = mulPat.matcher(firstAllowedMuls);
        sum += calculateSum(firstMatcher);

        String[] fileSplitByDont = completeFile.split(dontPat.pattern());
        for (int i = 1; i < fileSplitByDont.length; i++) {
            int indexOfDo = fileSplitByDont[i].indexOf("do");
            if (indexOfDo != -1) {
                String doMul = fileSplitByDont[i].substring(indexOfDo + 1);
                Matcher matcher = mulPat.matcher(doMul);
                sum += calculateSum(matcher);
            }
        }
        System.out.println(sum);
    }

    private static int calculateSum(Matcher firstMatcher) {
        int sum = 0;
        while (firstMatcher.find()) {
            String calculationAsString = firstMatcher.group(0);
            String[] stringSplitted = calculationAsString.split(",");
            List<Integer> numberString = new ArrayList<>();
            for (String string : stringSplitted) {
                numberString.add(Integer.parseInt(string.replaceAll("[^0-9]", "")));
            }
            sum += numberString.get(0) * numberString.get(1);
        }
        return sum;
    }

    private static List<String> readFile() {
        List<String> lineList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/DayThree/DayThree.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                lineList.add(line);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lineList;
    }

}
