package baseball;

import baseball.domain.EndResult;
import baseball.domain.Result;
import baseball.service.GameService;

public class BaseBallApplication {

    private final GameService gameService;

    public BaseBallApplication(GameService gameService) {
        this.gameService = gameService;
    }

    public void run() {
        gameService.start();
        while (true) {
            if (shouldFinishGame()) {
                break;
            }
        }
    }

    private boolean shouldFinishGame() {
        final String inputString = gameService.input();
        final Result result = gameService.getResult(inputString);
        gameService.print(result.getHint());

        if (result.canFinish()) {
            final EndResult endResult = gameService.restartInput();
            if (endResult.isFinish()) {
                return true;
            }

            gameService.restart();
        }

        return false;
    }
}
