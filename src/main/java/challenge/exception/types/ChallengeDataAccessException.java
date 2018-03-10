package challenge.exception.types;

/**
 * Created by rsanchpa on 29/09/2017.
 */
public class ChallengeDataAccessException extends BaseException {

    public ChallengeDataAccessException(String message) {
        super(message);
    }

    public ChallengeDataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
