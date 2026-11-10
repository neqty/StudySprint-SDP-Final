# Who is responsible for what? (4 students)

This is the **suggested task allocation**, not a claim that each named student has already written the supplied code. Before submission, each member needs to make genuine changes, run their tests and record their actual Git commits.

## 1. Erasyl - Builder + Strategy

**Assigned Java files**: `patterns/builder/StudyPlanBuilder.java`, `patterns/strategy/ScheduleStrategy.java`, `PriorityStrategy.java`, `ShortestStrategy.java`.

**Feature responsibility**: new plan configuration; selecting `Priority first` or `Shortest first` and explaining different schedules.

**What to show on defense**: create a new plan; run the two strategies on tasks with different lengths/priorities; point to `strategy.arrange(...)` in `ScheduleService.java`. Explain Builder's optional settings and Strategy's algorithm replacement.

**Simple live edit rehearsal**: add `Longest first` as a third strategy, connect it to scheduler and GUI combo, then test it.

## 2. Arsen - Factory Method + Decorator

**Assigned Java files**: `patterns/factory/TaskCreator.java`, `ReadingTaskCreator.java`, `PracticeTaskCreator.java`, `TaskCreators.java`, `model/ReadingTask.java`, `model/PracticeTask.java`, `patterns/decorator/SessionText.java`, `BasicSessionText.java`, `BreakDecorator.java`, `TipDecorator.java`.

**Feature responsibility**: two task types, study tips, optional breaks and tips in schedule lines.

**What to show**: add both Reading and Practice tasks, show that their tips differ, enable/disable breaks; explain `create` -> overridden `makeTask`, and wrapper delegation.

**Simple live edit rehearsal**: add an `ExtraBreakDecorator` with 10 minutes, or a `RevisionTask` type with its own creator.

## 3. Abylaikhan - Observer + Facade

**Assigned Java files**: `patterns/observer/PlanObserver.java`, `ActivityHistory.java`, `patterns/facade/StudyFacade.java`; integration with `ui/MainFrame.java`.

**Feature responsibility**: GUI event refresh, action history, simple public operations connecting task creation / scheduling / file saving.

**What to show**: change a task, open Activity; show `changed` notifying observers; trace UI click -> `StudyFacade` -> repository and scheduler.

**Simple live edit rehearsal**: add a new observer that counts changes or prints a message in console.

## 4. Adilzhan - Adapter + Bridge

**Assigned Java files**: `patterns/adapter/TaskImporter.java`, `OldCsvReader.java`, `LegacyCsvAdapter.java`, `patterns/bridge/ReportFormat.java`, `PlainTextFormat.java`, `HtmlFormat.java`, `StudyReport.java`, `SummaryReport.java`, `DetailedReport.java`.

**Feature responsibility**: import sample old-format CSV; create summary/detailed reports as TXT or HTML; handle invalid rows.

**What to show**: import `samples/legacy_tasks.csv`; export Detailed/HTML and Summary/TXT; show target vs adaptee and independent report/formats.

**Simple live edit rehearsal**: add `MarkdownFormat` implementing `ReportFormat`, selectable in GUI.

## Shared responsibilities

- Everyone explains the three entities, GUI, validation, saving, and at least one complete user journey.
- Everyone tests their own patterns and pushes **their own** commits to the shared repo.
- Erasyl: coordinate documentation and screenshots after review.
- Arsen: coordinate GUI form verification.
- Abylaikhan: coordinate error-case testing and persistence checks.
- Adilzhan: coordinate UML and export results.

## Git collaboration plan (genuine steps, no fabricated history)

1. One student creates an empty public repository and shares access with the team.
2. Commit initial Java project with an accurate author and AI-use disclosure.
3. Each member checks out a branch (`erasyl-builder-strategy`, `arsen-factory-decorator`, `abylai-observer-facade`, `adil-adapter-bridge`), independently adjusts their code, commits real changes, and opens a pull request/merge request.
4. Review code together, merge, run `test.bat`, and push the final report.
5. Copy the real repository URL into the PDF/report before Moodle submission.

**Never invent development history, dates, or contribution claims.**
