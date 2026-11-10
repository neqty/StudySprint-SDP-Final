package kz.studysprint.patterns.factory;

import kz.studysprint.model.*;
public class ReadingTaskCreator extends TaskCreator {
    @Override public StudyTask makeTask(String id, String title, String subject, int minutes, int priority, String notes) {
        return new ReadingTask(id, title, subject, minutes, priority, notes);
    }
}
