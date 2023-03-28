package baseball.domain;

public class EndResult {

    public static final String RESTART = "1";
    public static final String END = "2";
    private final boolean finish;

    private EndResult(boolean finish) {
        this.finish = finish;
    }

    public static EndResult from(String input) {
        if(input.equals(RESTART)) {
            return new EndResult(false);
        }

        if(input.equals(END)) {
            return new EndResult(true);
        }

        throw new IllegalArgumentException("숫자는 1 혹은 2여야 합니다.");
    }

    public boolean isFinish() {
        return finish;
    }
}
