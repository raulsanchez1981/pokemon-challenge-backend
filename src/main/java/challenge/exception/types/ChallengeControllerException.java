package challenge.exception.types;

/**
 * Created by rsanchpa on 29/09/2017.
 */
public class ChallengeControllerException extends BaseException {

    public ChallengeControllerException(String message) {
        super(message);
    }

    public ChallengeControllerException(String message, Throwable cause) {
        super(message, cause);
    }
}
