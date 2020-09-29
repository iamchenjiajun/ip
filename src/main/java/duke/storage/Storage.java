package duke.storage;

import duke.exception.DateTimeFormatException;
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

/**
 * Represents a file used to store the tasks.
 */
public class Storage {
    private final String fileLocation;
    private final String fileName;

    /**
     * Initializes the location and name of the file.
     *
     * @param fileLocation Location of the directory containing the file.
     * @param fileName Name of the file.
     */
    public Storage(String fileLocation, String fileName) {
        this.fileLocation = fileLocation;
        this.fileName = fileName;
    }

    /**
     * Checks if the file named fileName exists at fileLocation.
     * If the directories or file do not exist, the directories and file will be created.
     *
     * @throws IOException when creation of directories or file fails
     */
    public void checkFileExists() throws IOException {
        if (!Files.exists(Path.of(fileLocation))) {
            Files.createDirectories(Path.of(fileLocation));
        }
        if (!Files.exists(Path.of(fileLocation + fileName))) {
            Files.createFile(Path.of(fileLocation + fileName));
        }
    }

    /**
     * Loads and parses the file into an ArrayList of tasks.
     * Returns the ArrayList containing the tasks.
     *
     * @return ArrayList containing Tasks.
     * @throws FileNotFoundException If the file does not exist at fileLocation.
     * @throws FileFormatException If the tasks stored in the file do not adhere to the given format.
     * @throws DateTimeFormatException If the dates are in an invalid format.
     */
    public ArrayList<Task> loadTasks() throws FileNotFoundException, FileFormatException, DateTimeFormatException {
        ArrayList<Task> tasks = new ArrayList<>();

        File file = new File(fileLocation + fileName);
        Scanner in = new Scanner(file);
        while (in.hasNext()) {
            String line = in.nextLine();
            String[] taskDetails = line.split(" \\| ");

            // add the existing tasks
            switch (taskDetails[0]) {
            case "T":
                tasks.add(new Todo(taskDetails[2]));
                break;
            case "D":
                tasks.add(new Deadline(taskDetails[2], taskDetails[3]));
                break;
            case "E":
                tasks.add(new Event(taskDetails[2], taskDetails[3]));
                break;
            default:
                throw new FileFormatException();
                // Fallthrough
            }

            if (taskDetails[1].equals("1")) {
                tasks.get(tasks.size() - 1).markAsDone();
            }
        }

        return tasks;
    }

    /**
     * Saves an ArrayList of tasks to the file.
     *
     * @param tasks ArrayList containing Tasks.
     * @throws IOException If there are write errors.
     */
    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(fileLocation + fileName);
        for (Task task : tasks) {
            fw.write(task.toSaveString() + System.lineSeparator());
        }
        fw.close();
    }
}
