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

    public static void listTasks(List<Task> tasks, String filter) {

        if(tasks.isEmpty()) {
            
            System.out.println("No tasks found");
            return;
        }

        boolean found = false;

        for(Task t : tasks) {

            if(filter == null || t.getStatus().equals(filter)) {

                System.out.println(t.getId() + " | " + t.getStatus() + " | " + t.getDescription());
                found = true;
            }
        }

        if(!found) {

            System.out.println("No tasks found");
        }
    }

    public static Task findTaskByid(List<Task> tasks, int id) {
        
        for(Task t : tasks) {

            if(t.getId() == id) {

                return t;
            }
        }

        return null;
    }

    public static boolean updateTask(List<Task> tasks, int id, String newDescription) {

        Task t = findTaskByid(tasks, id);

        if(t == null) {

            return false;
        }

        t.setDescription(newDescription);
        t.setUpdatedAt(currentTimeStamp());

        return true;
    }

    public static boolean deleteTask(List<Task> tasks, int id) {

        Task t = findTaskByid(tasks, id);

        if(t == null) {

            return false;
        }

        tasks.remove(t);
        return true;
    }

    public static boolean markTask(List<Task> tasks, int id, String newStatus) {

        Task t = findTaskByid(tasks, id);

        if(t == null) {

            return false;
        }

        t.setStatus(newStatus);
        t.setUpdatedAt(currentTimeStamp());

        return true;
    }
}