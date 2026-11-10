package kz.studysprint;

import kz.studysprint.model.*;
import kz.studysprint.patterns.facade.StudyFacade;
import kz.studysprint.patterns.builder.StudyPlanBuilder;
import kz.studysprint.patterns.factory.TaskCreators;
import kz.studysprint.patterns.adapter.*;
import kz.studysprint.patterns.observer.ActivityHistory;
import kz.studysprint.service.ScheduleService;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.IOException;

// Simple automatic business-logic tests; no testing library is needed.
public class StudySprintTests {
    private static int passed = 0;
    private static void check(boolean value, String name) {
        if (!value) throw new AssertionError("FAILED: " + name);
        passed++;
        System.out.println("PASS " + passed + " - " + name);
    }
    private static boolean rejects(Runnable r) {
        try { r.run(); return false; }
        catch (IllegalArgumentException e) { return true; }
    }
    public static void main(String[] args) throws Exception {
        Path directory = Files.createTempDirectory("studysprint-tests-");
        Path saved = directory.resolve("data.txt");
        StudyFacade app = new StudyFacade(saved);
        check(Files.exists(saved) && app.getCurrent().getTasks().size() == 3, "sample data on first start");
        ActivityHistory history = new ActivityHistory();
        app.addObserver(history);
        app.newPlan("My tests");
        check(app.getCurrent().getName().equals("My tests"), "create a plan using Builder");
        app.addTask("Reading", "Read chapter", "Java", 60, 5, "notes");
        app.addTask("Practice", "Solve exercises", "Java", 20, 1, "practice");
        check(app.getCurrent().getTasks().size() == 2, "create two task types with Factory Method");
        check(!app.getCurrent().getTasks().get(0).getStudyTip().equals(app.getCurrent().getTasks().get(1).getStudyTip()), "different task behaviours");
        app.setOptions("Priority first", false, false);
        app.generateSchedule();
        check(app.displaySchedule().indexOf("Read chapter") < app.displaySchedule().indexOf("Solve exercises"), "priority Strategy ordering");
        app.setOptions("Shortest first", false, false);
        app.generateSchedule();
        check(app.displaySchedule().indexOf("Solve exercises") < app.displaySchedule().indexOf("Read chapter"), "shortest Strategy ordering");
        check(app.totalMinutes() == 80, "schedule minutes without Decorator");
        app.setOptions("Shortest first", true, true);
        app.generateSchedule();
        check(app.totalMinutes() == 90 && app.displaySchedule().contains("Tip:"), "break and tip Decorators");
        check(history.getMessages().stream().anyMatch(m -> m.contains("Generated schedule")), "Observer gets schedule event");
        String id = app.getCurrent().getTasks().get(0).getId();
        app.toggleComplete(id);
        check(app.getCurrent().getTasks().get(0).isCompleted() && app.getCurrent().getSessions().isEmpty(), "mark done invalidates schedule");
        StudyFacade restarted = new StudyFacade(saved);
        restarted.selectPlan(app.getCurrent().getId());
        check(restarted.getCurrent().getTasks().get(0).isCompleted(), "persistent save and reload");
        check(restarted.getReport(true, true).contains("<html") && restarted.getReport(true, false).contains("[DONE]"), "Bridge report types and formats");
        check(rejects(() -> TaskCreators.forType("Reading").create("", "Java", 20, 3, "")), "empty title rejected");
        check(rejects(() -> TaskCreators.forType("Reading").create("Task", "Java", 1, 3, "")), "duration below 5 rejected");
        check(rejects(() -> TaskCreators.forType("Practice").create("Task", "Java", 20, 8, "")), "priority above 5 rejected");
        check(rejects(() -> new StudyPlanBuilder().name(" ").build()), "empty plan name rejected");
        Path csv = directory.resolve("tasks.csv");
        Files.writeString(csv, "Review,History,25,3,Reading\\nQuiz,Math,15,5,Practice\\n".replace("\\n", "\n"), StandardCharsets.UTF_8);
        restarted.importLegacyCsv(csv);
        check(restarted.getCurrent().getTasks().size() == 4, "Adapter imports legacy CSV");
        Files.writeString(csv, "Bad row,Math,-9,3,Practice\n");
        int before = restarted.getCurrent().getTasks().size();
        boolean failed = false;
        try { restarted.importLegacyCsv(csv); } catch (IOException e) { failed = true; }
        check(failed && restarted.getCurrent().getTasks().size() == before, "invalid CSV rejected without partial import");
        String updateId = restarted.getCurrent().getTasks().get(1).getId();
        restarted.updateTask(updateId, "Reading", "Updated", "Java", 35, 4, "new");
        check(restarted.getCurrent().findTask(updateId).getTitle().equals("Updated"), "edit a task");
        restarted.removeTask(updateId);
        check(restarted.getCurrent().findTask(updateId) == null, "delete a task");
        Path export = directory.resolve("result.html");
        restarted.saveReport(export, true, true);
        check(Files.readString(export).contains("Updated") == false, "export correct state to HTML");
        StudyFacade again = new StudyFacade(saved);
        again.selectPlan(restarted.getCurrent().getId());
        check(again.getCurrent().getTasks().size() == 3, "edits and deletes persist");
        System.out.println("ALL " + passed + " TESTS PASSED");
    }
}
