import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    public static int getNextId(List<Task> tasks) {

        int maxId = 0;

        for(Task t : tasks) {

            if(t.getId() > maxId) {

                maxId = t.getId();
            }
        }

        return maxId + 1;
    }

    public static String currentTimeStamp() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

    public static int addTask(List<Task> tasks, String description) {

        int id = getNextId(tasks);
        String now = currentTimeStamp();

        Task newTask = new Task(id, description, "todo", now, now);
        tasks.add(newTask);

        return id;
    }

    public static void listTasks(List<Task> tasks) {

        if(tasks.isEmpty()) {
            
            System.out.println("No tasks found");
            return;
        }

        for(Task t : tasks) {

            System.out.println(t.getId() + " | " + t.getDescription() + " | " + t.getStatus());
        }
    }
}