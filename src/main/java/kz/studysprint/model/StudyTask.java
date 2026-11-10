package kz.studysprint.model;

// A task can be Reading or Practice. Each kind gives a different study tip.
public abstract class StudyTask {
    private final String id;
    private String title;
    private String subject;
    private int minutes;
    private int priority;
    private String notes;
    private boolean completed;

    public StudyTask(String id, String title, String subject, int minutes, int priority, String notes) {
        this.id = id;
        change(title, subject, minutes, priority, notes);
    }

    public void change(String title, String subject, int minutes, int priority, String notes) {
        if (title == null || title.trim().isEmpty()) throw new IllegalArgumentException("Task title is required.");
        if (subject == null || subject.trim().isEmpty()) throw new IllegalArgumentException("Subject is required.");
        if (minutes < 5 || minutes > 300) throw new IllegalArgumentException("Minutes must be from 5 to 300.");
        if (priority < 1 || priority > 5) throw new IllegalArgumentException("Priority must be from 1 to 5.");
        this.title = title.trim();
        this.subject = subject.trim();
        this.minutes = minutes;
        this.priority = priority;
        this.notes = notes == null ? "" : notes.trim();
    }

    public abstract String getType();
    public abstract String getStudyTip();
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getSubject() { return subject; }
    public int getMinutes() { return minutes; }
    public int getPriority() { return priority; }
    public String getNotes() { return notes; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}
