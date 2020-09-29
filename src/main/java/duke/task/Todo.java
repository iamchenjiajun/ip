package duke.task;

/**
 * Represents a todo created by the user.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toSaveString() {
        String isDoneString = (isDone ? "1" : "0");
        return "T | " + isDoneString + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
