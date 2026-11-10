package kz.studysprint.patterns.bridge;

import kz.studysprint.model.*;
import kz.studysprint.service.ScheduleService;
import java.util.*;
public class SummaryReport extends StudyReport {
    public SummaryReport(ReportFormat format) { super(format); }
    @Override protected List<String> makeLines(StudyPlan plan, ScheduleService scheduler) {
        long done = plan.getTasks().stream().filter(StudyTask::isCompleted).count();
        return Arrays.asList("Tasks: " + plan.getTasks().size(), "Completed: " + done,
            "Scheduling: " + plan.getStrategy(), "Sessions: " + plan.getSessions().size(),
            "Scheduled minutes (with breaks): " + scheduler.totalMinutes(plan));
    }
}
