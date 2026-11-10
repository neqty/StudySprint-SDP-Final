package kz.studysprint.model;

public class ReadingTask extends StudyTask {
    public ReadingTask(String id, String title, String subject, int minutes, int priority, String notes) {
        super(id, title, subject, minutes, priority, notes);
    }
    @Override public String getType() { return "Reading"; }
    @Override public String getStudyTip() { return "Read the material and write a short summary."; }
}
