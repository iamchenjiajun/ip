package duke.taskmanager;

import duke.exception.DateTimeFormatException;
import duke.exception.InvalidIndexException;
import duke.task.DatedTask;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskManager {
    private final ArrayList<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public TaskManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            System.out.println(index + "." + tasks.get(i));
        }
    }

    public void printTasksOnDate(String date) throws DateTimeFormatException {
        LocalDate targetDate;

        try {
            targetDate = LocalDate.parse(date, Task.DATE_PARSE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DateTimeFormatException(Ui.ERROR_DATE_FORMAT);
        }

        String dateString = targetDate.format(Task.DATE_PRINT_FORMATTER);
        System.out.println("Here's an overview of " + dateString);

        tasks.stream()
                .filter((task) -> task instanceof DatedTask)
                .filter((task) -> targetDate.equals(((DatedTask) task).getLocalDate()))
                .forEach(System.out::println);
    }

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

    public void findTask(String searchString) {
        System.out.println("Here's the tasks containing '" + searchString + "':");

        ArrayList<Task> filteredTasks = (ArrayList<Task>) tasks.stream()
                .filter((task) -> task.getDescription().toLowerCase().contains(searchString.toLowerCase()))
                .collect(Collectors.toList());

        for (int i = 0; i < filteredTasks.size(); i++) {
            int index = i + 1;
            System.out.println(index + "." + filteredTasks.get(i));
        }
    }

    public void markAsDone(int index) throws InvalidIndexException {
        try {
            tasks.get(index).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(index));
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException((Ui.ERROR_DONE_ARGUMENT));
        }
    }

    public void addDeadline(String description, String by) throws DateTimeFormatException {
        tasks.add(new Deadline(description, by));
        System.out.println("Added " + description + " as a Deadline.");
        System.out.println(tasks.get(tasks.size() - 1));
    }

    public void addTodo(String description) {
        tasks.add(new Todo(description));
        System.out.println("Added " + description + " as a Todo.");
        System.out.println(tasks.get(tasks.size() - 1));
    }

    public void addEvent(String description, String at) throws DateTimeFormatException {
        tasks.add(new Event(description, at));
        System.out.println("Added " + description + " as an Event.");
        System.out.println(tasks.get(tasks.size() - 1));
    }

    public int getTasksCount() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
