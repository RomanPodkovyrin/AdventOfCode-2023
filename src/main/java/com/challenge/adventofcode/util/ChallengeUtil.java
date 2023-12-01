package com.challenge.adventofcode.util;

import java.util.List;
import java.util.Map;

public class ChallengeUtil {

    public static final List<String> stringNumbers = List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
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
                    var allNums = line.replaceAll("\\D","");
                    return Integer.parseInt(String.valueOf(allNums.charAt(0)) + allNums.charAt(allNums.length() - 1));
                }).sum();
    }

    public static String replaceLast(String text, String regex, String replacement) {
        return text.replaceFirst("(?s)"+regex+"(?!.*?"+regex+")", replacement);
    }

    public static String convertStringNumsToInts(String list) {
        System.out.println("Before: " + list);
        for (Map.Entry<String, String> entry : values.entrySet()) {
            list = list.replaceAll(entry.getKey(), entry.getValue());
        }
        System.out.println("All numbers: " + list);
        return list;
    }
}
