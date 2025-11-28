package pairmatching.exception;

public class PairMatchException extends RuntimeException {
    public PairMatchException(String errorMessage) {
        throw new IllegalArgumentException("[ERROR]" + errorMessage);
    }
}
