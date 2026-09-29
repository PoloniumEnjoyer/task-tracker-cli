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

                if(args.length < 2) {

                    TaskManager.listTasks(tasks, null);
                }

                else {

                    String filter = args[1];
                    
                    if(!filter.equals("done") && !filter.equals("todo") && !filter.equals("in-progress")) {

                        System.out.println("Invalid filter. Use: done, todo, or in-progress.");
                        return;
                    }

                    TaskManager.listTasks(tasks, filter);
                }
            }

            else if(command.equals("update")) {

                if(args.length < 3) {

                    System.out.println("Usage: update <id> \"<new description>\"");
                    return;
                }

                int id;

                try {

                    id = Integer.parseInt(args[1]);
                }

                catch(NumberFormatException e) {

                    System.out.println("Invalid id: " + args[1]);
                    return;
                }

                boolean success = TaskManager.updateTask(tasks, id, args[2]);

                if(success) {

                    TaskStorage.saveTasks(tasks);
                    System.out.println("Task updated succesfully");
                }

                else {

                    System.out.println("No task found with ID: " + id);
                }
            }

            else if(command.equals("delete")) {

                if(args.length < 2) {

                    System.out.println("Usage: delete <id>");
                    return;
                }

                int id;

                try {

                    id = Integer.parseInt(args[1]);
                }

                catch(NumberFormatException e) {

                    System.out.println("Invalid id: " + args[1]);
                    return;
                }

                boolean success = TaskManager.deleteTask(tasks, id);

                if(success) {

                    TaskStorage.saveTasks(tasks);
                    System.out.println("Task deleted successfully");
                }

                else {

                    System.out.println("No task found with id: " + id);
                }
            }

            else if (command.equals("mark-in-progress")) {

                if(args.length < 2) {

                    System.out.println("Usage: mark-in-progress <id>");
                    return;
                }

                int id;

                try {

                    id = Integer.parseInt(args[1]);
                } 
                
                catch(NumberFormatException e) {

                    System.out.println("Invalid id: " + args[1]);
                    return;
                }

                boolean success = TaskManager.markTask(tasks, id, "in-progress");

                if (success) {

                    TaskStorage.saveTasks(tasks);
                    System.out.println("Task marked as in-progress");
                } 
                
                else {

                    System.out.println("No task found with ID: " + id);
                }
            }

            else if (command.equals("mark-done")) {

                if (args.length < 2) {

                    System.out.println("Usage: mark-done <id>");
                    return;
                }

                int id;

                try {

                    id = Integer.parseInt(args[1]);
                } 
                
                catch(NumberFormatException e) {

                    System.out.println("Invalid id: " + args[1]);
                    return;
                }

                boolean success = TaskManager.markTask(tasks, id, "done");

                if (success) {

                    TaskStorage.saveTasks(tasks);
                    System.out.println("Task marked as done");
                } 
                
                else {

                    System.out.println("No task found with ID: " + id);
                }
            }

            else {

                System.out.println("Unknown command: " + command);
            }
        }

        catch(IOException e) {

            System.out.println("Something went wrong: " + e.getMessage());
            return;
        }
    }
}