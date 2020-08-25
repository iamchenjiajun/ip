import java.util.Scanner;

public class Duke {

    public static void showDivider() {
        System.out.println("____________________________________________________________");
    }

    public static void greet() {
        showDivider();
        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?");
        showDivider();
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void echo(String line) {
        System.out.println(line);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner in = new Scanner(System.in);
        boolean isBye = false;

        greet();

        while (!isBye) {
            String line = in.nextLine();
            showDivider();

            switch(line) {
            case "bye":
                isBye = true;
                bye();
                break;
            default:
                echo(line);
                break;
            }

            showDivider();
        }
    }

}
