package duke.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String toSaveString() {
        String isDoneString = (isDone ? "1" : "0");
        return "T | " + isDoneString + " | " + description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
