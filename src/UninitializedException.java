/**
 * Custom error message to denote that object was not initialised correctly
 */

public class UninitializedException extends RuntimeException {
    public final String message;
    public UninitializedException(String msg){
        message = msg;
    }
}
