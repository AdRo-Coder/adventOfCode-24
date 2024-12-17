package DayFourteen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DayFourteen {

    public static void main(String[] args) {
        partOne(readInput("DayFourteenTestInput.csv"), 10, 6);
    }

    public static void partOne(List<Robot> robotList, int width, int height) {
        for (int i = 0; i < 100; i++) {
            for (Robot robot : robotList) {
                moveRobot(robot, width, height);
            }
        }

        countQuarters(robotList, width, height);

    }

    private static void countQuarters(List<Robot> robotList, int width, int height) {
        int topLeftQuarter = 0;
        int topRightQuarter = 0;
        int bottomLeftQuarter = 0;
        int bottomRightQuarter = 0;

        int widthMiddle = width / 2;
        int heightMiddle = height / 2;

        for (Robot robot : robotList) {
            if (0 <= robot.posX && robot.posX < widthMiddle && 0 <= robot.posY && robot.posY < heightMiddle) {
                topLeftQuarter++;
            } else if (widthMiddle < robot.posX && robot.posX <= width && 0 <= robot.posY && robot.posY < heightMiddle) {
                topRightQuarter++;
            } else if (0 <= robot.posX && robot.posX < widthMiddle && heightMiddle < robot.posY && robot.posY < height) {
                bottomLeftQuarter++;
            } else if (widthMiddle < robot.posX && robot.posX < width && heightMiddle <= robot.posY && robot.posY < height) {
                bottomRightQuarter++;
            }
        }

        System.out.println(topLeftQuarter + " " + topRightQuarter + " " + bottomLeftQuarter + " " + bottomRightQuarter);

        System.out.println(topLeftQuarter * topRightQuarter * bottomLeftQuarter * bottomRightQuarter);

    }

    private static void moveRobot(Robot robot, int width, int height) {
        robot.posX += robot.velX;
        robot.posY += robot.velY;

        if (robot.posX < 0) {
            robot.posX += width;
        } else if (robot.posX > width) {
            robot.posX -= width;
        }

        if (robot.posY < 0) {
            robot.posY += height;
        } else if (robot.posY > height) {
            robot.posY -= height;
        }
    }

    public static List<Robot> readInput(String filename) {
        List<Robot> robotList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/DayFourteen/" + filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(" ");
                int posX = 0;
                int posY = 0;
                int velX = 0;
                int velY = 0;
                posX = Integer.parseInt(arr[0].substring(2, arr[0].indexOf(",")));
                posY = Integer.parseInt(arr[0].substring(arr[0].indexOf(",") + 1));
                velX = Integer.parseInt(arr[1].substring(2, arr[1].indexOf(",")));
                velY = Integer.parseInt(arr[1].substring(arr[1].indexOf(",") + 1));
                robotList.add(new Robot(posX, posY, velX, velY));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return robotList;
    }

    public static class Robot {
        int posX;
        int posY;
        int velX;
        int velY;

        public Robot(int posX, int posY, int velX, int velY) {
            this.posX = posX;
            this.posY = posY;
            this.velX = velX;
            this.velY = velY;
        }

    }

}
