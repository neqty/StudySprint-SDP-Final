package kz.studysprint.patterns.bridge;

import kz.studysprint.model.*;
import kz.studysprint.service.ScheduleService;
import java.util.*;
public class DetailedReport extends StudyReport {
    public DetailedReport(ReportFormat format) { super(format); }
    @Override protected List<String> makeLines(StudyPlan plan, ScheduleService scheduler) {
        List<String> lines = new ArrayList<>();
        lines.add("Scheduling method: " + plan.getStrategy());
        lines.add("Breaks: " + plan.hasBreaks() + "; Study tips: " + plan.hasTips());
        for (StudyTask task : plan.getTasks()) {
            lines.add((task.isCompleted() ? "[DONE] " : "[TODO] ") + task.getTitle() +
                " | " + task.getSubject() + " | " + task.getType() + " | " + task.getMinutes() +
                " min | priority " + task.getPriority() + (task.getNotes().isEmpty() ? "" : " | " + task.getNotes()));
        }
        if (plan.getSessions().isEmpty()) lines.add("No schedule generated yet.");
        for (StudySession session : plan.getSessions()) {
            StudyTask task = plan.findTask(session.getTaskId());
            if (task != null) lines.add("Session " + session.getPosition() + ": " + scheduler.decorate(plan, task).describe().replace("\n", " "));
        }
        lines.add("Total schedule time: " + scheduler.totalMinutes(plan) + " minutes");
        return lines;
    }
}
