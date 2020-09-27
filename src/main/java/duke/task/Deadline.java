package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime byDateTime;

    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        this.byDateTime = LocalDateTime.parse(by, DATETIME_PARSE_FORMATTER);
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
