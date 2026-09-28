public class Task {
    
    private int id; 
    private String description;
    private String status;
    private String createdAt;
    private String updatedAt;

    public Task(int id, String description, String status, String createdAt, String updatedAt) {

        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // GETTERS
    public int getId() {

        return id;
    }

    public String getDescription() {

        return description;
    }

    public String getStatus() {

        return status;
    }

    public String getCreatedAt() {

        return createdAt;
    }

    public String getUpdatedAt() {

        return  updatedAt;
    }

    // SETTERS
    public void setDescription(String description) {

        this.description = description;
    }

    public void setStatus(String status) {

        this.status = status;
    }

    public void setUpdatedAt(String updatedAt) {

        this.updatedAt = updatedAt;
    }

    // MAKING JSON
    public String toJson() {

        return "{\n" + 
        "  \"id\": " + id + ",\n" +
        "  \"description\": \"" + description + "\",\n" +
        "  \"status\": \"" + status + "\",\n" + 
        "  \"createdAt\": \"" + createdAt + "\",\n" +
        "  \"updatedAt\": \"" + updatedAt + "\"\n" + 
        "}";
    }
}
