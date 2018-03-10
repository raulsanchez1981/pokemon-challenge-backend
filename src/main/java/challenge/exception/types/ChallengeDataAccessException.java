package challenge.exception.types;


public class ChallengeDataAccessException extends BaseException {

    public ChallengeDataAccessException(String message) {
        super(message);
    }

    public ChallengeDataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
