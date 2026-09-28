import java.time.LocalDateTime;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskStorage {

    public static List<Task> loadTasks() throws IOException {

        Path filePath = Path.of("tasks.json");

        // IF THE JSON FILE DOESNT EXIST THEN RETURN AN EMPTY LIST FOR THE USER TO MODIFY
        if(!Files.exists(filePath)) {

            return new ArrayList<>();
        }

        // IF THE CONTENTS OF THE JSON IS EMPTY THEN RETURN AN EMPTY LIST 
        String content = Files.readString(filePath);

        if(content.trim().isEmpty()) {

            return new ArrayList<>();
        }

        // IF THE JSON IS PRESENT AND NON EMPTY THEN PARSE THAT INTO TASKS
        return JsonHelper.parseTasksJson(content);
    }

    public static void saveTasks(List<Task> tasks) throws IOException {

        String json = JsonHelper.tasksToJson(tasks);
        Files.writeString(Path.of("tasks.json"), json);
    }
}