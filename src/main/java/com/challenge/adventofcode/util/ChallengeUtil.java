package com.challenge.adventofcode.util;

import java.util.List;
import java.util.Map;

public class ChallengeUtil {

    public static Map<String, String> values = Map.of(
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
}
