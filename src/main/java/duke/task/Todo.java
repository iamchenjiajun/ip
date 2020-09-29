package duke.task;

/**
 * Represents a todo created by the user.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the {@code Todo} to be saved.
     *
     * @return String representation of the {@code Todo} to be saved.
     */
    @Override
    public String toSaveString() {
        String isDoneString = (isDone ? "1" : "0");
        return "T | " + isDoneString + " | " + description;
    }

    /**
     * Returns the string representation of the {@code Todo} to be displayed.
     *
     * @return String representation of the task {@code Todo} be displayed.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
