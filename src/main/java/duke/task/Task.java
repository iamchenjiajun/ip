package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * Represents a task created by the user.
 */
public abstract class Task {
    public static final DateTimeFormatter DATE_PARSE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter DATE_PRINT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    public static final DateTimeFormatter DATETIME_PARSE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter DATETIME_PRINT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    protected String description;
    protected boolean isDone;

    /**
     * Initializes the description of the {@code Task}.
     *
     * @param description Description of the {@code Task}.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the String representing the state of the {@code Task}.
     * A tick is returned if the {@code Task} is done,
     * and a cross is returned if the {@code Task} is not done.
     *
     * @return String showing if the {@code Task} was done.
     */
    public String getStatusIcon() {
        return (isDone ? "[✓]" : "[✗]");
    }

    /**
     * Marks the {@code Task} as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the description of the {@code Task}.
     *
     * @return Description of the {@code Task}.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the string representation of the {@code Task} to be saved.
     *
     * @return String representation of the {@code Task} to be saved.
     */
    public abstract String toSaveString();

    /**
     * Returns the string representation of the {@code Task} to be displayed.
     *
     * @return String representation of the {@code Task} to be displayed.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }
}
