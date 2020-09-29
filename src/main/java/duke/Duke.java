package duke;

import duke.command.Command;
import duke.exception.FileFormatException;
import duke.exception.InvalidArgumentException;
import duke.exception.InvalidIndexException;
import duke.exception.UnknownCommandException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.taskmanager.TaskManager;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Represents the entry point of the Duke program.
 * Initializes and manages the TaskManager, Ui and Storage, and handles user interaction.
 */
public class Duke {
    public static final String FILE_LOCATION = "./data/";
    public static final String FILE_NAME = "duke.txt";

    private TaskManager taskManager;
    private final Ui ui;
    private final Storage storage;

    /**
     * Starts the program by greeting the user and loading existing tasks from file.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILE_LOCATION, FILE_NAME);

        ui.greet();

        try {
            storage.checkFileExists();
            taskManager = new TaskManager(storage.loadTasks());
            ui.showLoadSuccessful();
        } catch (IOException e) {
            ui.showLoadError();
            taskManager = new TaskManager();
        } catch (FileFormatException e) {
            ui.showFileFormatError();
            taskManager = new TaskManager();
        } finally {
            ui.showDivider();
        }
    }

    /**
     * Starts the read-evaluate-print loop to read, parse and execute user input.
     */
    public void run() {
        boolean isBye = false;
        Parser parser = new Parser();

        do {
            String line = ui.readCommand();
            ui.showDivider();
            try {
                Command command = parser.parseCommand(line);
                command.execute(taskManager, ui, storage);
                isBye = command.isBye();
            } catch (UnknownCommandException e) {
                System.out.println(e.getErrorMessage());
            } catch (InvalidArgumentException e) {
                System.out.println(e.getErrorMessage());
            } catch (InvalidIndexException e) {
                System.out.println(e.getErrorMessage());
            }
            ui.showDivider();
        } while (!isBye);
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
