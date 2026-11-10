# Final defense - short English explanations

## 90-second shared demonstration

1. "Our project is Study Sprint, a desktop study planner for university students. It solves the problem of organizing tasks before exams."
2. "We have three main entities: StudyPlan, StudyTask, and StudySession."
3. "First I create a task, then select a scheduling strategy, enable optional breaks and tips, and generate my schedule."
4. "I can mark tasks as done, view the activity, and export the report. The app saves data locally, even after restart."
5. "We implemented eight integrated patterns. Each member is responsible for explaining and modifying two of them."

## Erasyl

**Builder:** "StudyPlanBuilder creates a StudyPlan step by step. I can choose the name, strategy, breaks and tips before calling `build()`. It avoids a long constructor in UI code. Its limitation is one more class for a simple model."

**Strategy:** "The `ScheduleStrategy` interface has the `arrange` method. PriorityStrategy sorts by descending priority, and ShortestStrategy sorts by duration. The user can change the algorithm without changing the task model. The limitation is more classes for each new strategy."

**Live change:** add `LongestStrategy` sorting `StudyTask::getMinutes` descending; add `Longest first` in `StudyPlan.setStrategy`, `ScheduleService`, and `MainFrame` combo. Explain that the Strategy interface stays the same.

## Arsen

**Factory Method:** "TaskCreator is the creator base. ReadingTaskCreator returns ReadingTask; PracticeTaskCreator returns PracticeTask. Each task has its own study tip. New task types need a new creator."

**Decorator:** "BasicSessionText describes a scheduled task. BreakDecorator adds five minutes, while TipDecorator adds instructions. They use the same SessionText interface and can be combined. Too many wrappers can become difficult to trace."

**Live change:** create `LongBreakDecorator` that adds 10 minutes (copy BreakDecorator and change 5 to 10), then call it from `ScheduleService.decorate`.

## Abylaikhan

**Observer:** "StudyFacade notifies PlanObserver when data changes. ActivityHistory records messages and the GUI refreshes automatically. This keeps updates separate from storage. A limitation is that a bad observer can affect a notification."

**Facade:** "The GUI uses StudyFacade instead of calling storage, factory, importer, scheduler, and report classes directly. The code is easier to use, but Facade can grow too large if we add many features."

**Live change:** write a `ConsoleObserver implements PlanObserver` with `System.out.println(message)` and register it in MainFrame after ActivityHistory.

## Adilzhan

**Adapter:** "OldCsvReader returns raw String arrays. LegacyCsvAdapter implements TaskImporter and converts those arrays to real StudyTasks. Old import code can be reused. The limitation is our simple CSV format does not support quoted commas."

**Bridge:** "StudyReport is the abstraction: SummaryReport and DetailedReport. ReportFormat is the implementation: PlainTextFormat and HtmlFormat. We can combine any report type with any format without creating four special report classes."

**Live change:** implement `MarkdownFormat implements ReportFormat`, returning a heading and bullet lines; add it as a format choice.

## Questions all four must answer

- **Why eight patterns?** The assignment requires six distinct patterns and at least two per each of four students, so we selected eight patterns that solve real problems.
- **Where is the MVC-like separation?** `ui/` holds display, `model/` holds domain, `service/` and `patterns/` handle logic, `storage/` handles files.
- **Where is persistence?** `PlanRepository.save/load` stores all plans, tasks and sessions under `data/plans.txt`.
- **What is input validation?** StudyTask verifies nonempty text, 5-300 minutes, priority 1-5; invalid imports are rejected.
- **Where is the user journey?** New plan -> add/configure tasks -> generate schedule -> preview/save report.
- **What should improve later?** Date-based scheduling, reminder notifications, cloud sync, tests for GUI, richer CSV format.

**Important:** The teacher may request a different live code change. Practice making one safely, compiling, and checking behavior rather than memorizing only these examples.
