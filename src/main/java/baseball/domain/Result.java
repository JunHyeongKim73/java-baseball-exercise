package baseball.domain;

public class Result {

    private static final int ALL_NUMBERS_MATCH = 3;

    private final String hint;
    private final boolean finish;

    private Result(String hint, boolean canFinish) {
        this.hint = hint;
        this.finish = canFinish;
    }

    public static Result from(int balls, int strikes) {
        final String hint = getHintFromBallAndStrike(balls, strikes);
        final boolean canFinish = strikes == ALL_NUMBERS_MATCH;
        return new Result(hint, canFinish);
    }

    public String getHint() {
        return hint;
    }

    public boolean canFinish() {
        return finish;
    }

    private static String getHintFromBallAndStrike(int balls, int strikes) {
        if (balls == 0 && strikes == 0) {
            return "낫싱";
        }
        if (strikes == 3) {
            return "3스트라이크\n3개의 숫자를 모두 맞히셨습니다! 게임 종료";
        }
        if (balls != 0 && strikes != 0) {
            return balls + "볼 " + strikes + "스트라이크";
        }
        if (balls != 0) {
            return balls + "볼";
        }
        return strikes + "스트라이크";
    }
}
