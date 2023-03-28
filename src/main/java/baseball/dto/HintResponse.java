package baseball.dto;

public class HintResponse {
    private final int strikes;
    private final int balls;

    public HintResponse(int strikes, int balls) {
        this.strikes = strikes;
        this.balls = balls;
    }

    public int getStrikes() {
        return strikes;
    }

    public int getBalls() {
        return balls;
    }
}
