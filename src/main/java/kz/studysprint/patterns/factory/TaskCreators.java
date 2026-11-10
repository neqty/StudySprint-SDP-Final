package kz.studysprint.patterns.factory;

public class TaskCreators {
    public static TaskCreator forType(String type) {
        if ("Reading".equals(type)) return new ReadingTaskCreator();
        if ("Practice".equals(type)) return new PracticeTaskCreator();
        throw new IllegalArgumentException("Choose Reading or Practice.");
    }
}
