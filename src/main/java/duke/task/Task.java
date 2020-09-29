package duke.task;

import java.time.format.DateTimeFormatter;

public abstract class Task {
    public static final DateTimeFormatter DATE_PARSE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter DATE_PRINT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    public static final DateTimeFormatter DATETIME_PARSE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter DATETIME_PRINT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[✓]" : "[✗]");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return description;
    }

    public abstract String toSaveString();

    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }
}
