package duke.exception;

import duke.Duke;

public class UnknownCommandException extends DukeException {

    public String getErrorMessage() {
        return Duke.ERROR_UNKNOWN_COMMAND;
    }
}
