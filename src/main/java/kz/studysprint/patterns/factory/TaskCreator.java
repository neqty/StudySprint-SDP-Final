package kz.studysprint.patterns.factory;

import kz.studysprint.model.StudyTask;
import java.util.UUID;

// FACTORY METHOD: subclasses decide which concrete StudyTask is created.
public abstract class TaskCreator {
    public StudyTask create(String title, String subject, int minutes, int priority, String notes) {
        return makeTask(UUID.randomUUID().toString(), title, subject, minutes, priority, notes);
    }
    public abstract StudyTask makeTask(String id, String title, String subject, int minutes, int priority, String notes);
}
