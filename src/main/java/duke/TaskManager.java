package duke;

import duke.exception.FileFormatException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {
    public static final String FILE_LOCATION = "./data/";
    public static final String FILE_NAME = "duke.txt";
    private final ArrayList<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void checkFileExists() throws IOException {
        if (!Files.exists(Path.of(FILE_LOCATION))) {
            Files.createDirectories(Path.of(FILE_LOCATION));
        }
        if (!Files.exists(Path.of(FILE_LOCATION + FILE_NAME))) {
            Files.createFile(Path.of(FILE_LOCATION + FILE_NAME));
        }
    }

    public void loadTasks() throws FileNotFoundException, FileFormatException {
        File file = new File(FILE_LOCATION + FILE_NAME);
        Scanner in = new Scanner(file);
        while (in.hasNext()) {
            String line = in.nextLine();
            String[] taskDetails = line.split(" \\| ");

            // add the existing tasks
            switch (taskDetails[0]) {
            case "T":
                addTodo(taskDetails[2], false);
                break;
            case "D":
                addDeadline(taskDetails[2], taskDetails[3], false);
                break;
            case "E":
                addEvent(taskDetails[2], taskDetails[3], false);
                break;
            default:
                throw new FileFormatException();
                // Fallthrough
            }

            // mark task as done
            if (taskDetails[1].equals("1")) {
                markAsDone(getTasksCount() - 1, false);
            }
        }
    }

    public void saveTasks() throws IOException {
        FileWriter fw = new FileWriter(FILE_LOCATION + FILE_NAME);
        for (int i = 0; i < getTasksCount(); i++) {
            fw.write(tasks.get(i).toSaveString() + System.lineSeparator());
        }
        fw.close();
    }

    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            System.out.println(index + "." + tasks.get(i));
        }
    }

    public void deleteTask(int index) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(index));
        tasks.remove(index);
        System.out.println("Now you have " + getTasksCount() + " tasks in the list");
    }

    public void markAsDone(int index, boolean shouldPrintMessage) {
        tasks.get(index).markAsDone();
        if (shouldPrintMessage) {
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(index));
        }
    }

    public void addDeadline(String description, String by, boolean shouldPrintMessage) {
        tasks.add(new Deadline(description, by));
        if (shouldPrintMessage) {
            System.out.println("Added " + description + " as a Deadline.");
            System.out.println(tasks.get(tasks.size() - 1));
        }
    }

    public void addTodo(String description, boolean shouldPrintMessage) {
        tasks.add(new Todo(description));
        if (shouldPrintMessage) {
            System.out.println("Added " + description + " as a Todo.");
            System.out.println(tasks.get(tasks.size() - 1));
        }
    }

    public void addEvent(String description, String at, boolean shouldPrintMessage) {
        tasks.add(new Event(description, at));
        if (shouldPrintMessage) {
            System.out.println("Added " + description + " as an Event.");
            System.out.println(tasks.get(tasks.size() - 1));
        }
    }

    public int getTasksCount() {
        return tasks.size();
    }
}
