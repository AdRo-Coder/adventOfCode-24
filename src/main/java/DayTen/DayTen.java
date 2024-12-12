package DayTen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DayTen {


    public static void main(String[] args) {
        //int[][] matrix = readFile("DayTenTestInput.csv");
        int[][] matrix = readFile("DayTen.csv");
        //partOne(matrix);
        partOneOptionTwo(matrix);
    }

    private static void partOneOptionTwo(int[][] matrix) {
        int count = 0;
        List<int[]> zeroPositions = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    zeroPositions.add(new int[]{i, j});
                }
            }
        }

        for (int[] zeroPosition : zeroPositions) {
            count += move(matrix, zeroPosition[0], zeroPosition[1], new HashSet<>());
        }
        System.out.println(count);
    }

//    private static void partOne(int[][] matrix) {
//        int count = 0;
//        List<int[]> zeroPositions = new ArrayList<>();
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[i].length; j++) {
//                if (matrix[i][j] == 0) {
//                    zeroPositions.add(new int[]{i, j});
//                }
//            }
//        }
//
//        for (int[] zeroPosition : zeroPositions) {
//            int posI = zeroPosition[0];
//            int posJ = zeroPosition[1];
//            int number = matrix[posI][posJ];
//            List<String> possibleMovesForZeros = movePossible(matrix, number, posI, posJ);
//            for (String move : possibleMovesForZeros) {
//                number = 1;
//                int[] move1 = move(posI, posJ, move);
//                posI = move1[0];
//                posJ = move1[1];
//                for (String move2 : movePossible(matrix, number, posI, posJ)) {
//                    int[] moveTwo = move(posI, posJ, move2);
//                    number = 2;
//                    posI = moveTwo[0];
//                    posJ = moveTwo[1];
//                    for (String move3 : movePossible(matrix, number, posI, posJ)) {
//                        int[] moveThree = move(posI, posJ, move3);
//                        number = 3;
//                        posI = moveThree[0];
//                        posJ = moveThree[1];
//                        for (String move4 : movePossible(matrix, number, posI, posJ)) {
//                            number = 4;
//                            int[] moveFour = move(posI, posJ, move4);
//                            posI = moveFour[0];
//                            posJ = moveFour[1];
//                            for (String move5 : movePossible(matrix, number, posI, posJ)) {
//                                number = 5;
//                                int[] moveFive = move(posI, posJ, move5);
//                                posI = moveFive[0];
//                                posJ = moveFive[1];
//                                for (String move6 : movePossible(matrix, number, posI, posJ)) {
//                                    number = 6;
//                                    int[] moveSix = move(posI, posJ, move6);
//                                    posI = moveSix[0];
//                                    posJ = moveSix[1];
//                                    for (String move7 : movePossible(matrix, number, posI, posJ)) {
//                                        number = 7;
//                                        int[] moveSeven = move(posI, posJ, move7);
//                                        posI = moveSeven[0];
//                                        posJ = moveSeven[1];
//                                        for (String move8 : movePossible(matrix, number, posI, posJ)) {
//                                            number = 8;
//                                            int[] moveEight = move(posI, posJ, move8);
//                                            posI = moveEight[0];
//                                            posJ = moveEight[1];
//                                            count += movePossible(matrix, number, posI, posJ).size();
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        System.out.println(count);
//    }

    private static int move(int[][] matrix, int posI, int posJ, Set<String> visited) {
        if (matrix[posI][posJ] == 9) {
            return 1;
        }

        int score = 0;
        if (visited.contains(posI + "" + posJ)) {
            List<String> possibleMoves = movePossible(matrix, matrix[posI][posJ], posI, posJ);
            for (String move : possibleMoves) {
                int[] newPos = move(posI, posJ, move, visited);
                switch (move) {
                    case "up" -> visited.add(newPos[0] + 1 + "" + newPos[1]);
                    case "down" -> visited.add(newPos[0] - 1 + "" + newPos[1]);
                    case "right" -> visited.add(newPos[0] + "" + newPos[1] + 1);
                    case "left" -> visited.add(newPos[0] + "" + (newPos[1] - 1));
                    default -> visited.add(newPos[0] + "" + newPos[1]);
                }
                score += move(matrix, newPos[0], newPos[1], visited);
            }
        }

        return score;
    }

    private static int[] move(int posI, int posJ, String move, Set<String> visited) {
        switch (move) {
            case "up" -> {
                return new int[]{posI + 1, posJ};
            }
            case "down" -> {
                return new int[]{posI - 1, posJ};
            }
            case "right" -> {
                return new int[]{posI, posJ + 1};
            }
            case "left" -> {
                return new int[]{posI, posJ - 1};
            }
            default -> {
                return new int[]{posI, posJ};
            }
        }
    }

    private static List<String> movePossible(int[][] matrix, int number, int posI, int posJ) {
        List<String> possibleMoves = new ArrayList<>();
        if (posI >= 0 && posJ >= 0) {

            if (posI + 1 < matrix.length && matrix[posI + 1][posJ] == number + 1) {
                possibleMoves.add("up");
            }
            if (posI - 1 >= 0 && matrix[posI - 1][posJ] == number + 1) {
                possibleMoves.add("down");
            }
            if (posJ >= 1 && posJ + 1 < matrix[0].length && matrix[posI][posJ + 1] == number + 1) {
                possibleMoves.add("right");
            }
            if (posJ - 1 >= 0 && matrix[posI][posJ - 1] == number + 1) {
                possibleMoves.add("left");
            }
        }
        return possibleMoves;
    }

    private static void partTwo() {
    }

    private static int[][] readFile(String fileName) {
        String[][] stringMatrix;
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/DayTen/" + fileName))) {
            List<String[]> dataList = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = new String[line.length()];
                for (int i = 0; i < line.length(); i++) {
                    arr[i] = String.valueOf(line.charAt(i));
                }
                dataList.add(arr);
            }
            stringMatrix = dataList.toArray(new String[0][]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int[][] intMatrix = new int[stringMatrix.length][stringMatrix[0].length];
        for (int i = 0; i < stringMatrix.length; i++) {
            for (int j = 0; j < stringMatrix[i].length; j++) {
                intMatrix[i][j] = Integer.parseInt(stringMatrix[i][j]);
            }
        }

        return intMatrix;
    }
}
