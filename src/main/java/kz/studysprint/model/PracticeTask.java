package kz.studysprint.model;

public class PracticeTask extends StudyTask {
    public PracticeTask(String id, String title, String subject, int minutes, int priority, String notes) {
        super(id, title, subject, minutes, priority, notes);
    }
    @Override public String getType() { return "Practice"; }
    @Override public String getStudyTip() { return "Solve exercises and check the answers."; }
}
