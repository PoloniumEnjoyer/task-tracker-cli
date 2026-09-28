import java.time.LocalDateTime;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonHelper {

    public static String tasksToJson(List<Task> tasks) {

        StringBuilder sb = new StringBuilder();
        sb.append("[\n");

        for(int i = 0; i < tasks.size(); i++) {

            sb.append(tasks.get(i).toJson());

            if(i < tasks.size() - 1) {

                sb.append(",");
            }

            sb.append("\n");
        }

        sb.append("]");
        return sb.toString();
    }

    public static String extractStringValue(String json, String key) {

        String searchKey = "\"" + key + "\"";
        int keyIndex = json.indexOf(searchKey);
        int colonIndex = json.indexOf(":", keyIndex);
        int firstQuote = json.indexOf("\"", colonIndex);
        int secondQuote = json.indexOf("\"", firstQuote + 1);

        return json.substring(firstQuote + 1, secondQuote);
    }

    public static int extractIntValue(String json, String key) {

        String searchKey = "\"" + key + "\"";
        int keyIndex = json.indexOf(searchKey);
        int colonIndex = json.indexOf(":", keyIndex);

        int start = colonIndex + 1;

        while(json.charAt(start) == ' ' || json.charAt(start) == '\n') {

            start++;
        }

        int end = start;

        while(Character.isDigit(json.charAt(end))) {

            end++;
        }

        String numberText = json.substring(start, end);
        return Integer.parseInt(numberText);
    }

    public static Task parseTask(String json) {

        int id = extractIntValue(json, "id");
        String description = extractStringValue(json, "description");
        String status = extractStringValue(json, "status");
        String createdAt = extractStringValue(json, "createdAt");
        String updatedAt = extractStringValue(json, "updatedAt");

        return new Task(id, description, status, createdAt, updatedAt);
    }

    public static List<String> splitTasksJson(String json) {

        List<String> taskChunks = new ArrayList<>();
        int i = 0;

        while(i < json.length()) {

            if(json.charAt(i) == '{') {

                int start = i;
                int end = json.indexOf('}', start);
                taskChunks.add(json.substring(start, end));
                i = end + 1;
            }
            
            else {

                i++;
            }
        }

        return taskChunks;
    }

    public static List<Task> parseTasksJson(String json) {

        List<String> chunks = splitTasksJson(json);
        List<Task> tasks = new ArrayList<>();

        for(String chunk : chunks) {

            Task t = parseTask(chunk);
            tasks.add(t);
        }

        return tasks;
    }
}