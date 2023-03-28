package baseball.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RandomNumberGenerator implements NumberGenerator {

    @Override
    public String generate(int size, int start, int end) {
        List<Integer> integers = new ArrayList<>();
        while (integers.size() < size) {
            int randomNumber = Randoms.pickNumberInRange(start, end);
            if (!integers.contains(randomNumber)) {
                integers.add(randomNumber);
            }
        }

        return integers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(""));
    }
}
