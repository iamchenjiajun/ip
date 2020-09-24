package duke.ui;

public class Ui {
    public static final String DUKE_DIVIDER = ">>>>++----------------------------------";
    public static final String DUKE_GREETINGS = "Hello! I'm Duke!\n"
            + "What can I do for you?";
    public static final String DUKE_LOGO = "Hello from\n"
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String DUKE_BYE = "Bye. Hope to see you again soon!";
    public static final String TASK_LOAD_SUCCESSFUL = "Tasks loaded successfully.";

    public Ui() {

    }

    public void showDivider() {
        System.out.println(DUKE_DIVIDER);
    }

    public void greet() {
        System.out.println(DUKE_LOGO);
        showDivider();
        System.out.println(DUKE_GREETINGS);
        showDivider();
    }

    public void showByeMessage() {
        System.out.println(DUKE_BYE);
    }

    public void showLoadSuccessful() {
        System.out.println(TASK_LOAD_SUCCESSFUL);
    }
}
