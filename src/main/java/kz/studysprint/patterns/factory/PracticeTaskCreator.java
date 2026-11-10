package kz.studysprint.patterns.factory;

import kz.studysprint.model.*;
public class PracticeTaskCreator extends TaskCreator {
    @Override public StudyTask makeTask(String id, String title, String subject, int minutes, int priority, String notes) {
        return new PracticeTask(id, title, subject, minutes, priority, notes);
    }
}
