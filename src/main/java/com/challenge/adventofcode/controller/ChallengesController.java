package com.challenge.adventofcode.controller;

import com.challenge.adventofcode.util.ChallengeUtil;
import com.challenge.adventofcode.util.InputReader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                        .map(ChallengeUtil::convertStringNumsToInts
                        ).toList());
    }

}
