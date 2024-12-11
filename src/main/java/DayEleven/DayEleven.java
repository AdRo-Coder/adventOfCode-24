package DayEleven;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DayEleven {


    public static void main(String[] args) {
        partOne(readFile("DayElevenTestInput.csv"));
        partOne(readFile("DayEleven.csv"));
    }

    private static void partOne(List<Stone> stones) {
//        List<Stone> stoneList = new ArrayList<>(stones);
        Stream<Stone> stoneList = stones.stream();


        for (int i = 0; i < 75; i++) {
            stoneList = splitStones(stoneList);
        }
        System.out.println(stoneList.count());

    }

    private static void writeFile(String fileName, List<Stone> stonesToWrite) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/java/DayEleven/" + fileName))) {
            StringBuilder sb = new StringBuilder();
            for (Stone stone : stonesToWrite) {
                sb.append(stone.value).append(" ");
            }
            bw.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Stone> splitStones(List<Stone> stonesToSplit) {
        List<Stone> listToReturn = new ArrayList<>();

        stonesToSplit.forEach(stone -> {
            if (stone.value == 0) {
                listToReturn.add(new Stone(1));
            } else if (String.valueOf(stone.value).length() % 2 == 0) {
                String value = String.valueOf(stone.value);

                listToReturn.add(new Stone((Long.parseLong(value.substring(0, value.length() / 2)))));
                listToReturn.add(new Stone((Long.parseLong(value.substring(value.length() / 2)))));
            } else {
                listToReturn.add(new Stone(stone.value * 2024));
            }
        });

        return listToReturn;
    }

    private static Stream<Stone> splitStones(Stream<Stone> stonesToSplit) {
        return stonesToSplit.flatMap(stone -> {
            if (stone.value == 0) {
                return Stream.of(new Stone(1));
            } else if (String.valueOf(stone.value).length() % 2 == 0) {
                String value = String.valueOf(stone.value);
                return Stream.of(
                        new Stone(Long.parseLong(value.substring(0, value.length() / 2))),
                        new Stone(Long.parseLong(value.substring(value.length() / 2)))
                );
            } else {
                return Stream.of(new Stone(stone.value * 2024));
            }
        });
    }

    private static List<Stone> readFile(String fileName) {
        List<Stone> stones = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/DayEleven/" + fileName))) {
            String line = br.readLine();
            String[] arr = line.split(" ");

            for (String s : arr) {
                stones.add(new Stone(Long.parseLong(s)));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stones;
    }

    public static class Stone {
        long value;

        public Stone(long value) {
            this.value = value;
        }

        public long getValue() {
            return value;
        }

        public void setValue(long value) {
        }
    }


}
