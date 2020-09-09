public class UnknownCommandException extends DukeException {
    private final String errorMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    public String getErrorMessage() {
        return errorMessage;
    }
}
