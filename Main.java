import java.time.LocalDateTime;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        if(args.length == 0) {

            System.out.println("Usage: <command> [arguments]");
            return ;
        }

        try {

            List<Task> tasks = TaskStorage.loadTasks();
            String command = args[0];

            if(command.equals("add")) {

                if(args.length < 2) {
                    
                    System.out.println("Please provide a task description");
                    return;
                }

                int id = TaskManager.addTask(tasks, args[1]);
                TaskStorage.saveTasks(tasks);
                System.out.println("Task added successfully (ID: " + id + ")");
            }

            else if(command.equals("list")) {

                TaskManager.listTasks(tasks);
            }

            else {

                System.out.println("Unknown command: " + command);
            }
        }

        catch(IOException e) {

            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}