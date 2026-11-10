package kz.studysprint.patterns.strategy;

import kz.studysprint.model.StudyTask;
import java.util.*;
public class ShortestStrategy implements ScheduleStrategy {
    @Override public List<StudyTask> arrange(List<StudyTask> tasks) {
        List<StudyTask> ordered = new ArrayList<>();
        for (StudyTask task : tasks) if (!task.isCompleted()) ordered.add(task);
        ordered.sort(Comparator.comparingInt(StudyTask::getMinutes));
        return ordered;
    }
}
