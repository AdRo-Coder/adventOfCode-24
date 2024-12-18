package DayTen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Day10 {
    record Coords(int x, int y) {
    }

    public static void main(String[] args) throws IOException {
        char[][] grid;
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/java/DayTen/DayEleven.csv"))) {
            grid = reader.lines().map(String::toCharArray).toArray(char[][]::new);
        }

        solve1(grid);
        solve2(grid);
    }

    private static void solve1(char[][] grid) {
        int totalScore = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == '0') {
                    totalScore += scoreTrailHead(grid, new Coords(col, row), '0', new HashSet<>());
                }
            }
        }
        System.out.println(totalScore);
    }

    private static int scoreTrailHead(char[][] grid, Coords currPos, char curr, Set<Coords> visited) {
        visited.add(currPos);
        if (curr == '9') {
            return 1;
        }

        int score = 0;
        Coords nextPosition = new Coords(currPos.x + 1, currPos.y);
        if (inBounds(grid, nextPosition)
                && !visited.contains(nextPosition)
                && grid[nextPosition.y][nextPosition.x] == curr + 1) {
            score += scoreTrailHead(grid, nextPosition, (char) (curr + 1), visited);
        }

        nextPosition = new Coords(currPos.x, currPos.y + 1);
        if (inBounds(grid, nextPosition)
                && !visited.contains(nextPosition)
                && grid[nextPosition.y][nextPosition.x] == curr + 1) {
            score += scoreTrailHead(grid, nextPosition, (char) (curr + 1), visited);
        }

        nextPosition = new Coords(currPos.x - 1, currPos.y);
        if (inBounds(grid, nextPosition)
                && !visited.contains(nextPosition)
                && grid[nextPosition.y][nextPosition.x] == curr + 1) {
            score += scoreTrailHead(grid, nextPosition, (char) (curr + 1), visited);
        }

        nextPosition = new Coords(currPos.x, currPos.y - 1);
        if (inBounds(grid, nextPosition)
                && !visited.contains(nextPosition)
                && grid[nextPosition.y][nextPosition.x] == curr + 1) {
            score += scoreTrailHead(grid, nextPosition, (char) (curr + 1), visited);
        }
        return score;
    }

    private static void solve2(char[][] grid) {
        int totalRating = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == '0') {
                    totalRating += rateTrailHead(grid, new Coords(col, row), '0', new HashSet<>());
                }
            }
        }
        System.out.println(totalRating);
    }

    private static int rateTrailHead(char[][] grid, Coords currPos, char curr, HashSet<Coords> currPath) {
        if (curr == '9') {
            return 1;
        }

        int rating = 0;
        Coords nextPosition = new Coords(currPos.x + 1, currPos.y);
        if (inBounds(grid, nextPosition)
                && !currPath.contains(nextPosition)
                && grid[nextPosition.y][nextPosition.x] == curr + 1) {
            currPath.add(nextPosition);
            rating += rateTrailHead(grid, nextPosition, (char) (curr + 1), currPath);
            currPath.remove(nextPosition);
        }

        nextPosition = new Coords(currPos.x, currPos.y + 1);
        if (inBounds(grid, nextPosition)
                && !currPath.contains(nextPosition)
                && grid[nextPosition.y][nextPosition.x] == curr + 1) {
            currPath.add(nextPosition);
            rating += rateTrailHead(grid, nextPosition, (char) (curr + 1), currPath);
            currPath.remove(nextPosition);
        }

        nextPosition = new Coords(currPos.x - 1, currPos.y);
        if (inBounds(grid, nextPosition)
                && !currPath.contains(nextPosition)
                && grid[nextPosition.y][nextPosition.x] == curr + 1) {
            currPath.add(nextPosition);
            rating += rateTrailHead(grid, nextPosition, (char) (curr + 1), currPath);
            currPath.remove(nextPosition);
        }

        nextPosition = new Coords(currPos.x, currPos.y - 1);
        if (inBounds(grid, nextPosition)
                && !currPath.contains(nextPosition)
                && grid[nextPosition.y][nextPosition.x] == curr + 1) {
            currPath.add(nextPosition);
            rating += rateTrailHead(grid, nextPosition, (char) (curr + 1), currPath);
            currPath.remove(nextPosition);
        }
        return rating;
    }

    private static boolean inBounds(char[][] grid, Coords position) {
        return position.y >= 0 && position.y < grid.length && position.x >= 0 && position.x < grid[position.y].length;
    }
}