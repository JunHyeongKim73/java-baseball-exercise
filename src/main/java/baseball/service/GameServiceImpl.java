package baseball.service;

import baseball.domain.Computer;
import baseball.domain.EndResult;
import baseball.domain.Result;
import baseball.dto.HintResponse;
import camp.nextstep.edu.missionutils.Console;

public class GameServiceImpl implements GameService{

    private final Computer computer;

    public GameServiceImpl(Computer computer) {
        this.computer = computer;
    }

    public void start() {
        print("숫자 야구 게임을 시작합니다.");
        computer.start();
    }

    public void restart() {
        computer.start();
    }

    public Result getResult(String inputString) {
        final HintResponse response = computer.getHint(inputString);

        return Result.from(response.getBalls(), response.getStrikes());
    }

    public EndResult restartInput() {
        print("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        final String input = Console.readLine();

        return EndResult.from(input);
    }

    public String input() {
        print("숫자를 입력해주세요 : ");
        return Console.readLine();
    }

    public void print(String string) {
        System.out.println(string);
    }
}
