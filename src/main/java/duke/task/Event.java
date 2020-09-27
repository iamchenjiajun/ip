package duke.task;

import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime atDateTime;

    public Event(String description, String at) {
        super(description);
        this.atDateTime = LocalDateTime.parse(at, DATETIME_PARSE_FORMATTER);
    }

    public String toSaveString() {
        String isDoneString = (isDone ? "1" : "0");
        String dateTimeString = atDateTime.format(DATETIME_PARSE_FORMATTER);
        return "E | " + isDoneString + " | " + description + " | " + dateTimeString;
    }

    @Override
    public String toString() {
        String dateTimeString = atDateTime.format(DATETIME_PRINT_FORMATTER);
        return "[E]" + super.toString() + " (at: " + dateTimeString + ")";
    }
}
