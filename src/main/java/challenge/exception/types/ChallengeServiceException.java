package challenge.exception.types;

/**
 * Created by rsanchpa on 29/09/2017.
 */
public class ChallengeServiceException extends BaseException {

    public ChallengeServiceException(String message) {
        super(message);
    }

    public ChallengeServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
