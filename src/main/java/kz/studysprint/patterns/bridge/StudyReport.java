package kz.studysprint.patterns.bridge;

import kz.studysprint.model.StudyPlan;
import kz.studysprint.service.ScheduleService;
import java.util.List;

// BRIDGE abstraction side: summaries and details work with both output formats.
public abstract class StudyReport {
    protected final ReportFormat format;
    public StudyReport(ReportFormat format) { this.format = format; }
    public String create(StudyPlan plan, ScheduleService scheduler) {
        return format.write("Study Sprint: " + plan.getName(), makeLines(plan, scheduler));
    }
    protected abstract List<String> makeLines(StudyPlan plan, ScheduleService scheduler);
}
