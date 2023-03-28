package baseball.service;

@FunctionalInterface
public interface NumberGenerator {
    String generate(int size, int start, int end);
}
