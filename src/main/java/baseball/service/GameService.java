package baseball.service;

import baseball.domain.EndResult;
import baseball.domain.Result;

public interface GameService {
    void start();
    void restart();
    EndResult restartInput();
    Result getResult(String input);
    String input();
    void print(String string);
}
