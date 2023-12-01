package com.challenge.adventofcode.util;

import java.util.Arrays;
import java.util.List;

public class InputReader {

    public static List<String> getLines(String body){
        return Arrays.stream(body.split("\n")).toList();
    }
}
