package baseball;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import baseball.domain.Computer;
import baseball.domain.Result;
import baseball.service.GameServiceImpl;
import baseball.service.NumberGenerator;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class GameServiceImplTest {

    final NumberGenerator numberGenerator = (size, start, end) -> "123";
    final Computer computer = new Computer(numberGenerator);
    final GameServiceImpl gameServiceImpl = new GameServiceImpl(computer);

    public static Stream<Arguments> messageByInputString() {
        return Stream.of(
                Arguments.of("456", "낫싱", false),
                Arguments.of("123", "3스트라이크\n3개의 숫자를 모두 맞히셨습니다! 게임 종료", true),
                Arguments.of("132", "2볼 1스트라이크", false),
                Arguments.of("231", "3볼", false),
                Arguments.of("145", "1스트라이크", false)
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    void null혹은_빈값을_입력하면_예외를_던진다(String inputString) {
        assertThatThrownBy(() -> gameServiceImpl.getResult(inputString))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "12", "1234"})
    void 세_자리가_아닌_숫자를_입력하면_예외를_던진다(String inputString) {
        assertThatThrownBy(() -> gameServiceImpl.getResult(inputString))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"112", "122", "111"})
    void 세_자리수지만_중복된_숫자가_존재하면_예외를_던진다(String inputString) {
        assertThatThrownBy(() -> gameServiceImpl.getResult(inputString))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("messageByInputString")
    void 서로_다른_세_자리수에_대한_결과를_얻는다(String inputString, String message, boolean canFinish) {
        // given
        gameServiceImpl.start();

        // when
        final Result result = gameServiceImpl.getResult(inputString);

        // then
        assertAll(
                () -> assertThat(result.getHint()).isEqualTo(message),
                () -> assertThat(result.canFinish()).isEqualTo(canFinish)
        );
    }
}