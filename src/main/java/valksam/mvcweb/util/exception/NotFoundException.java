package valksam.mvcweb.util.exception;

/**
 * Created by Valk on 15.01.16.
 */
public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
