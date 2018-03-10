package challenge.exception.types;


public class ChallengeControllerException extends BaseException {

    public ChallengeControllerException(String message) {
        super(message);
    }

    public ChallengeControllerException(String message, Throwable cause) {
        super(message, cause);
    }
}
