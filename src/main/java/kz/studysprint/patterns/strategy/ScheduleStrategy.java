package kz.studysprint.patterns.strategy;

import kz.studysprint.model.StudyTask;
import java.util.List;

// STRATEGY: users switch algorithms without changing the scheduler.
public interface ScheduleStrategy { List<StudyTask> arrange(List<StudyTask> tasks); }
