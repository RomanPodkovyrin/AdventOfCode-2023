package com.challenge.adventofcode.controller;

import com.challenge.adventofcode.util.ChallengeUtil;
import com.challenge.adventofcode.util.InputReader;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "api/v1/challenges")
public class ChallengesController {

    @PostMapping(value = "/c1")
    public int solveChallenge1(@RequestBody String body) {
        return ChallengeUtil.sumFirstAndLastInt(InputReader.getLines(body));
    }

    @PostMapping(value = "/c1p2")
    public int solveChallenge1part2(@RequestBody String body) {
        return ChallengeUtil.sumFirstAndLastInt(
                InputReader
                        .getLines(body)
                        .stream()
                        .map(ChallengeUtil::convertStringNumsToInts)
                        .toList());
    }

    @PostMapping(value = "/c2")
    public int solveChallenge2(@RequestBody String body) {
        return InputReader.getLines(body)
                .stream()
                .mapToInt(ChallengeUtil::findPossibleGames)
                .sum();
    }

    @PostMapping(value = "/c2p2")
    public int solveChallenge2part2(@RequestBody String body) {
        return InputReader.getLines(body)
                .stream()
                .mapToInt(line -> ChallengeUtil.calculatePowerSet(line.split(":")[1]))
                .sum();
    }

//    @PostMapping(value = "/c3")
//    public int solveChallenge3(@RequestBody String body) {
//        char [][] ar = new char[1][1];
////        array_name[row_index][column_index] = value;
//        List<String> lines = InputReader.getLines(body);
//        List<Integer> engineNumbers = List.of();
////        engineNumbers.add()
//        lines.stream().forEach(l -> {
//            for (char ch: l.toCharArray()) {
//
//            }
//            l.charAt()
//        });
//        System.out.println(InputReader.getLines(body));
////        return InputReader.getLines(body)
////                .stream()
////                .mapToInt(line -> ChallengeUtil.calculatePowerSet(line.split(":")[1]))
////                .sum();
//
//        return 0;
//    }


    @PostMapping(value = "/c4")
    public int solveChallenge4(@RequestBody String body) {
        return InputReader.getLines(body)
                .stream()
                .mapToInt(line -> ChallengeUtil.getWinningTotal(line.split(":")[1]))
                .sum();
    }

    @PostMapping(value = "/c4p2")
    public int solveChallenge4p2(@RequestBody String body) {
        List<Integer> cards = ChallengeUtil.getScratchcardsOfScratchcards(body);
        return cards.stream().mapToInt(e -> e).sum();
    }

    //    @ExceptionHandler(RuntimeException.class)
//    public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
//        return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

}
