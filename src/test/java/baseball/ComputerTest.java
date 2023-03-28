package baseball;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import baseball.domain.Computer;
import baseball.dto.HintResponse;
import baseball.service.NumberGenerator;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class ComputerTest {

    private NumberGenerator numberGenerator;

    public static Stream<Arguments> ballsAndStrikesByNumbers() {
        return Stream.of(
                Arguments.of("135", "246", 0, 0),
                Arguments.of("135", "146", 0, 1),
                Arguments.of("135", "136", 0, 2),
                Arguments.of("135", "135", 0, 3),
                Arguments.of("135", "461", 1, 0),
                Arguments.of("135", "431", 1, 1),
                Arguments.of("135", "521", 2, 0),
                Arguments.of("135", "153", 2, 1),
                Arguments.of("135", "351", 3, 0),
                Arguments.of("589", "895", 3, 0),
                Arguments.of("589", "589", 0, 3)
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    void null혹은_빈값이면_예외를_던진다(String number) {
        // given
        numberGenerator = (size, start, end) -> number;
        final Computer computer = new Computer(numberGenerator);

        // when & then
        assertThatThrownBy(computer::start)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "1a", "12b"})
    void 숫자가_아닌_문자가_포함되어_있으면_예외를_던진다(String number) {
        // given
        numberGenerator = (size, start, end) -> number;
        final Computer computer = new Computer(numberGenerator);

        // when & then
        assertThatThrownBy(computer::start)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "10", "120"})
    void 숫자0이_포함되어_있으면_예외를_던진다(String number) {
        // given
        numberGenerator = (size, start, end) -> number;
        final Computer computer = new Computer(numberGenerator);

        // when & then
        assertThatThrownBy(computer::start)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "12", "1234"})
    void 세_자리가_아닌_숫자이면_예외를_던진다(String number) {
        // given
        numberGenerator = (size, start, end) -> number;
        final Computer computer = new Computer(numberGenerator);

        // when & then
        assertThatThrownBy(computer::start)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"112", "122", "111"})
    void 세_자리수지만_중복된_숫자가_존재하면_예외를_던진다(String number) {
        // given
        numberGenerator = (size, start, end) -> number;
        final Computer computer = new Computer(numberGenerator);

        // when & then
        assertThatThrownBy(computer::start)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "257", "587", "135", "589"})
    void 컴퓨터가_숫자를_생성한다(String number) {
        // given
        numberGenerator = (size, start, end) -> number;
        final Computer computer = new Computer(numberGenerator);

        // when & then
        assertThatCode(computer::start)
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @MethodSource("ballsAndStrikesByNumbers")
    void 각각의_경우에_따른_볼과_스트라이크_개수를_확인한다(String generatedNumber, String request, int balls, int strikes) {
        // given
        numberGenerator = (size, start, end) -> generatedNumber;
        final Computer computer = new Computer(numberGenerator);
        computer.start();

        // when
        final HintResponse response = computer.getHint(request);

        // then
        assertAll(
                () -> assertThat(response.getBalls()).isEqualTo(balls),
                () -> assertThat(response.getStrikes()).isEqualTo(strikes)
        );
    }
}