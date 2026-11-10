package kz.studysprint.patterns.decorator;

import kz.studysprint.model.StudyTask;
public class BasicSessionText implements SessionText {
    private final StudyTask task;
    public BasicSessionText(StudyTask task) { this.task = task; }
    @Override public String describe() { return task.getTitle() + " (" + task.getMinutes() + " min)"; }
    @Override public int totalMinutes() { return task.getMinutes(); }
}
