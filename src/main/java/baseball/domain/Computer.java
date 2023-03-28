package baseball.domain;

import baseball.service.NumberGenerator;
import baseball.dto.HintResponse;
import java.util.Objects;

public class Computer {

    private static final int NUMBER_SIZE = 3;
    private static final int START = 1;
    private static final int END = 9;
    private final NumberGenerator generator;
    private String number;

    public Computer(NumberGenerator generator) {
        this.generator = generator;
    }

    public void start() {
        final String generatedNumber = generator.generate(NUMBER_SIZE, START, END);
        validateNumber(generatedNumber);

        this.number = generatedNumber;
    }

    private void validateNumber(String number) {
        validateNull(number);
        validateInteger(number);
        validateLength(number);
        validateDuplication(number);
    }

    private void validateNull(String number) {
        if (Objects.isNull(number)) {
            throw new IllegalArgumentException("숫자는 null일 수 없습니다.");
        }
    }

    private void validateInteger(String number) {
        final boolean isAllIntegersBetweenOneToNine = number.chars()
                .map(Character::getNumericValue)
                .allMatch(intNumber -> START <= intNumber && intNumber <= END);
        if (!isAllIntegersBetweenOneToNine) {
            throw new IllegalArgumentException(number + "는 모두 숫자(1~9)로 이루어져 있지 않습니다.");
        }
    }

    private void validateLength(String number) {
        if (number.length() != NUMBER_SIZE) {
            throw new IllegalArgumentException("숫자는 세 자리여야 합니다.");
        }
    }

    private void validateDuplication(String number) {
        final long distinctCount = number.chars()
                .distinct()
                .count();

        if (distinctCount != number.length()) {
            throw new IllegalArgumentException("중복된 숫자가 존재합니다.");
        }
    }

    public HintResponse getHint(String request) {
        validateNumber(request);

        final int balls = getBalls(request);
        final int strikes = getStrikes(request);

        return new HintResponse(strikes, balls - strikes);
    }

    private int getBalls(String request) {
        return Math.toIntExact(request.chars()
                .filter(character -> this.number.indexOf(character) != -1)
                .count());
    }

    private int getStrikes(String request) {
        int strikes = 0;
        for (int i = 0; i < NUMBER_SIZE; i++) {
            if (this.number.charAt(i) == request.charAt(i)) {
                strikes++;
            }
        }

        return strikes;
    }
}
