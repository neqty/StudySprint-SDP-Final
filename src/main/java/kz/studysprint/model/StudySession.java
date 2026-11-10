package kz.studysprint.model;

// A generated schedule entry points to an existing task.
public class StudySession {
    private final String taskId;
    private final int position;
    public StudySession(String taskId, int position) {
        this.taskId = taskId;
        this.position = position;
    }
    public String getTaskId() { return taskId; }
    public int getPosition() { return position; }
}
