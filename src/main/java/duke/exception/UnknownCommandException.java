package duke.exception;

import duke.ui.Ui;

public class UnknownCommandException extends DukeException {

    public String getErrorMessage() {
        return Ui.ERROR_UNKNOWN_COMMAND;
    }
}
