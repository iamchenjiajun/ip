package duke.task;

import duke.exception.DateTimeFormatException;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime byDateTime;

    public Deadline(String description, String by) throws DateTimeFormatException {
        super(description);
        try {
            this.byDateTime = LocalDateTime.parse(by, DATETIME_PARSE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DateTimeFormatException(Ui.ERROR_DATE_FORMAT);
        }
    }

    public String toSaveString() {
        String isDoneString = (isDone ? "1" : "0");
        String dateTimeString = byDateTime.format(DATETIME_PARSE_FORMATTER);
        return "D | " + isDoneString + " | " + description + " | " + dateTimeString;
    }

    @Override
    public String toString() {
        String dateTimeString = byDateTime.format(DATETIME_PRINT_FORMATTER);
        return "[D]" + super.toString() + " (by: " + dateTimeString + ")";
    }
}
