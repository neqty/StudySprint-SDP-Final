package kz.studysprint.patterns.decorator;

import kz.studysprint.model.StudyTask;
public class TipDecorator implements SessionText {
    private final SessionText inner;
    private final StudyTask task;
    public TipDecorator(SessionText inner, StudyTask task) { this.inner = inner; this.task = task; }
    @Override public String describe() { return inner.describe() + "\n     Tip: " + task.getStudyTip(); }
    @Override public int totalMinutes() { return inner.totalMinutes(); }
}
