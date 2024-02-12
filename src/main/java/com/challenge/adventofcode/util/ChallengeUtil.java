package com.challenge.adventofcode.util;

import java.util.*;

public class ChallengeUtil {

    public static final Map<String, String> values = Map.of(
            "one", "one1one",
            "two", "two2two",
            "three", "three3three",
            "four", "four4four",
            "five", "five5five",
            "six", "six6six",
            "seven", "seven7seven",
            "eight", "eight8eight",
            "nine", "nine9nine"
    );

    public static Map<String, Integer> cubeValues = Map.of(
            "red", 12,
            "green", 13,
            "blue", 14
    );

    public static int sumFirstAndLastInt(List<String> list) {
        return list.stream()
                .mapToInt(line -> {
                    var onlyNumbers = line.replaceAll("\\D", "");
                    return Integer.parseInt(String.valueOf(onlyNumbers.charAt(0)) + onlyNumbers.charAt(onlyNumbers.length() - 1));
                }).sum();
    }

    public static String convertStringNumsToInts(String list) {
        for (Map.Entry<String, String> entry : values.entrySet()) {
            list = list.replaceAll(entry.getKey(), entry.getValue());
        }
        return list;
    }

    public static Boolean areCubeConstrintsMeet(String text) {
        for (Map.Entry<String, Integer> entry : cubeValues.entrySet()) {
            if (text.contains(entry.getKey()) && (Integer.parseInt(text.split(" ")[1]) > entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    private static Map<String, Integer> calculateMinVal(String text, Map<String, Integer> cubeValuesMin) {

        for (Map.Entry<String, Integer> entry : cubeValuesMin.entrySet()) {
            if (text.contains(entry.getKey())) {
                cubeValuesMin.put(entry.getKey(), Math.max(entry.getValue(), Integer.parseInt(text.split(" ")[1])));
            }
        }
        return cubeValuesMin;
    }

    public static int calculatePowerSet(String text) {
        Map<String, Integer> cubeValuesMin = new HashMap<>(Map.of(
                "red", 0,
                "green", 0,
                "blue", 0
        ));

        var vals = Arrays.stream(text.split("[;,]")).toList();
        for (String val : vals) {
            calculateMinVal(val, cubeValuesMin);
        }
        var powerSet = 1;
        for (Map.Entry<String, Integer> entry : cubeValuesMin.entrySet()) {
            powerSet = powerSet * entry.getValue();
        }
        return powerSet;
    }

    public static int findPossibleGames(String line) {
        var sets = line.split(":")[1];
        var id = Integer.parseInt(line.split(":")[0].split(" ")[1]);
        var check = Arrays
                .stream(sets.split(";"))
                .anyMatch(set -> Arrays
                        .stream(set.split(","))
                        .anyMatch(draw -> !areCubeConstrintsMeet(draw)));
        if (check) return 0;
        return id;
    }

    public static int getNumberOfMatchingNumbers(String line) {
        var count = 0;
        var splitLine = line.split("\\|");
        var winningNumbers = Arrays.stream(splitLine[0].split(" ")).filter(n -> !Objects.equals(n, "")).toList();
        var myNumbers = Arrays.stream(splitLine[1].split(" ")).filter(n -> !Objects.equals(n, "")).toList();
        for (String winningNumber : winningNumbers) {
            count = myNumbers.contains(winningNumber) ? count + 1 : count;
        }
        return count;
    }

    public static List<Integer> getScratchcardsOfScratchcards(String body) {
        var lines = InputReader.getLines(body);
        List<Integer> cards = new ArrayList<>(Collections.nCopies(lines.size(), 1));
        for (int i = 0; i < lines.size(); i++) {
            var line = lines.get(i);
            var wins = getNumberOfMatchingNumbers(line.split(":")[1]);
            for (int j = i + 1; j < lines.size(); j++) {
                if (wins <= 0) {
                    break;
                }
                cards.set(j, cards.get(j) + (cards.get(i)));
                wins -= 1;
            }
        }
        return cards;
    }

    public static int getWinningTotal(String line) {
        var count = getNumberOfMatchingNumbers(line) - 1;
        var result = count < 0 ? 0 : Math.pow(2, count);
        return (int) result;
    }

    public static int getSumOfAdjacentNumbers(List<String> lines, char[][] ar) {
        var sum = 0;
        var adjecent =false;
        StringBuilder currentNumber = new StringBuilder();
        for (int row = 0; row < lines.size(); row++) {
            for (int column = 0; column < lines.getFirst().length(); column++) {
                Character ch = null;
                var i = column;

                do {
                    ch = lines.get(row).charAt(i);
                    if (Character.isDigit(ch)) {
                        currentNumber.append(ch);
                        if ((ar[row][i]) == 'x') {
                            adjecent = true;
                        }
                    } else {
                        break;
                    }
                    i++;
                } while( i < lines.getFirst().length());

                 column = i;
                if (adjecent) {
                    sum+= Integer.parseInt(currentNumber.toString());
                    adjecent = false;
                }
                currentNumber = new StringBuilder();

            }
        }
        return sum;
    }

    public static char[][] buildMatrix(List<String> lines) {
        char[][] ar = new char[lines.size()][lines.getFirst().length()];
        for (int row = 0; row < lines.size(); row++) {
            for (int column = 0; column < lines.getFirst().length(); column++) {
                var ch = lines.get(row).charAt(column);
                if (!(Character.isDigit(ch) || ch == '.')) {
                    int top = Math.min(row + 1, lines.size());
                    int bottom = Math.max(row - 1,0);
                    int left = Math.max(column - 1, 0);
                    int right = Math.min(column + 1, lines.getFirst().length());
                    ar[top][left] = 'x';
                    ar[top][column] = 'x';
                    ar[top][right] = 'x';
                    ar[row][left] = 'x';
                    ar[row][right] = 'x';
                    ar[bottom][left] = 'x';
                    ar[bottom][column] = 'x';
                    ar[bottom][right] = 'x';
                }
            }
        }
        return ar;
    }
}
