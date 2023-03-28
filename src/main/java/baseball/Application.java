package baseball;

import baseball.domain.Computer;
import baseball.service.GameService;
import baseball.service.GameServiceImpl;
import baseball.service.NumberGenerator;
import baseball.service.RandomNumberGenerator;

public class Application {

    public static void main(String[] args) {
        final NumberGenerator numberGenerator = new RandomNumberGenerator();
        final Computer computer = new Computer(numberGenerator);
        final GameService gameService = new GameServiceImpl(computer);

        final BaseBallApplication baseBallApplication = new BaseBallApplication(gameService);
        baseBallApplication.run();
    }
}
