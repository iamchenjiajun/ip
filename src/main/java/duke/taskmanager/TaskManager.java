package duke.taskmanager;

import duke.exception.InvalidIndexException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * An object which manages a list of tasks.
 */
public class TaskManager {
    private final ArrayList<Task> tasks;

    /**
     * Creates a TaskManager object with an empty list of tasks.
     */
    public TaskManager() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskManager object with an existing list of tasks.
     *
     * @param tasks An ArrayList containing tasks.
     */
    public TaskManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Prints a numbered list of the tasks.
     */
    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            System.out.println(index + "." + tasks.get(i));
        }
    }

    /**
     * Deletes the task at the given index.
     *
     * @param index Index referring to the task to be deleted.
     * @throws InvalidIndexException If the index is out of range.
     */
    public void deleteTask(int index) throws InvalidIndexException {
        try {
            Task removedTask = tasks.get(index);
            tasks.remove(index);
            System.out.println("Noted. I've removed this task:");
            System.out.println(removedTask);
            System.out.println("Now you have " + getTasksCount() + " tasks in the list");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException(Ui.ERROR_DELETE_ARGUMENT);
        }
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param index Index referring to the task to be marked.
     * @throws InvalidIndexException If the index is out of range.
     */
    public void markAsDone(int index) throws InvalidIndexException {
        try {
            tasks.get(index).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(index));
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException((Ui.ERROR_DONE_ARGUMENT));
        }
    }

    /**
     * Adds a {@code Deadline} to the list of tasks.
     *
     * @param description Description of the deadline.
     * @param by Date of the deadline.
     */
    public void addDeadline(String description, String by) {
        tasks.add(new Deadline(description, by));
        System.out.println("Added " + description + " as a Deadline.");
        System.out.println(tasks.get(tasks.size() - 1));
    }

    /**
     * Adds a {@code Todo} to the list of tasks.
     *
     * @param description Description of the todo.
     */
    public void addTodo(String description) {
        tasks.add(new Todo(description));
        System.out.println("Added " + description + " as a Todo.");
        System.out.println(tasks.get(tasks.size() - 1));
    }

    /**
     * Adds an {@code Event} to the list of tasks.
     *
     * @param description Description of the event.
     * @param at Date of the event.
     */
    public void addEvent(String description, String at) {
        tasks.add(new Event(description, at));
        System.out.println("Added " + description + " as an Event.");
        System.out.println(tasks.get(tasks.size() - 1));
    }

    /**
     * Returns the length of the list containing the tasks.
     *
     * @return Length of the list containing the tasks.
     */
    public int getTasksCount() {
        return tasks.size();
    }

    /**
     * Returns an ArrayList containing the tasks.
     *
     * @return ArrayList containing the tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
