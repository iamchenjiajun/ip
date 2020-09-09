public class InvalidArgumentException extends DukeException {
    private String errorMessage = null;

    public InvalidArgumentException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
