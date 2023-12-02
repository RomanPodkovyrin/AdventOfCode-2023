package com.challenge.adventofcode.controller;

import com.challenge.adventofcode.util.ChallengeUtil;
import com.challenge.adventofcode.util.InputReader;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/challenges")
public class ChallengesController {

    @GetMapping(value = "/c1")
    public int solveChallenge1(@RequestBody String body) {
        return ChallengeUtil.sumFirstAndLastInt(InputReader.getLines(body));
    }

    @GetMapping(value = "/c1p2")
    public int solveChallenge1part2(@RequestBody String body) {
        return ChallengeUtil.sumFirstAndLastInt(
                InputReader
                        .getLines(body)
                        .stream()
                        .map(ChallengeUtil::convertStringNumsToInts)
                        .toList());
    }

    @GetMapping(value = "/c2")
    public int solveChallenge2(@RequestBody String body) {
        return InputReader.getLines(body)
                .stream()
                .mapToInt(ChallengeUtil::findPossibleGames)
                .sum();
    }

    @GetMapping(value = "/c2p2")
    public int solveChallenge2part2(@RequestBody String body) {
        return InputReader.getLines(body)
                .stream()
                .mapToInt(line -> ChallengeUtil.calculatePowerSet(line.split(":")[1]))
                .sum();
    }

//    @ExceptionHandler(RuntimeException.class)
//    public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
//        return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

}
