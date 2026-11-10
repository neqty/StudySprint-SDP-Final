# Study Sprint - Final Project

**Course:** Software Design Patterns  
**Team:** Erasyl, Arsen, Abylaikhan, Adilzhan  
**Language:** Java 17 or later, Swing desktop GUI. No external libraries, account, Internet, or database setup needed.

## What is it?

Study Sprint helps a student create a study plan, add Reading/Practice tasks, choose how tasks should be ordered, generate a schedule, mark tasks as finished, and export a plan as a TXT/HTML report. It saves the plans automatically. A sample plan loads on the first run.

The application contains **3 related entities**: `StudyPlan`, `StudyTask` (with Reading/Practice variants), and `StudySession`.

## Start on Windows (recommended)

1. Install **JDK 17+**. Check in PowerShell: `java -version` and `javac -version`.
2. Extract the ZIP. Open `StudySprint_Final` as a project in **IntelliJ IDEA**.
3. Set **Project SDK** to JDK 17 or newer.
4. Find `src/main/java/kz/studysprint/App.java` and click **Run App.main()**.
5. Alternatively, in Command Prompt from the project folder, run `run.bat`.

On Linux/macOS: `chmod +x run.sh test.sh` and `./run.sh`.

**No Maven required.** If IDEA does not automatically mark Java folders, right-click `src/main/java` -> Mark Directory as -> Sources Root and `src/test/java` -> Test Sources Root.

## Test commands

- Windows: `test.bat`
- Linux/macOS: `./test.sh`
- IntelliJ: Run `src/test/java/kz/studysprint/StudySprintTests.java`

There are **22 automatic business-logic checks**; the last line must say `ALL 22 TESTS PASSED`. They include creation, both scheduling strategies, notifications, report formats, CSV import, persistence and invalid inputs.

## Demo in 3 minutes

1. Start the app and see the example plan `Exam preparation`, with 3 tasks.
2. Click **Add task**, create a Practice task named `Finish homework` with 25 minutes, priority 5.
3. Click **Edit** to change the task; click **Done / Undo** to change its status.
4. Choose **Priority first**, check `Add 5-minute breaks` and `Show study tips`, then click **Generate schedule**.
5. Change strategy to **Shortest first** and generate again. Compare the order.
6. Open **Reports**, select `Detailed` + `HTML`, press **Preview**, then **Save file**.
7. Open **Activity**: Observer updates are visible.
8. Close/reopen the application: tasks still exist.
9. Optional: click **Import CSV** and choose `samples/legacy_tasks.csv`.

**Main journey:** create a plan -> configure tasks/options -> generate a schedule -> preview/export the result.

## Interface controls

- **New / Rename / Delete plan:** manage plans (the last remaining plan cannot be deleted).
- **Add / Edit / Done / Undo / Delete:** manage tasks.
- **Import CSV:** use Adapter to read the old CSV format.
- **Apply options:** save method, breaks and tips (old schedule is cleared).
- **Generate schedule:** creates ordered StudySessions.
- **Reports -> Summary / Detailed, TXT / HTML:** Bridge combinations.
- **Activity:** updates when plan data changes (Observer).

## Data / sample files

- The sample plan is created automatically **only if `data/plans.txt` does not exist**.
- All changes are saved to `data/plans.txt` as UTF-8 text with encoded user strings.
- The `data/` folder is ignored by Git because local study plans are personal.
- To reset the sample, close the application and delete the **entire** `data` folder.
- Old CSV must have **exactly five comma-separated columns** (no header): `title,subject,minutes,priority,type`.
- `minutes` must be 5-300, priority must be 1-5, type must be `Reading` or `Practice`.
- This basic CSV reader intentionally does not support quoted commas.

## Eight patterns (2 assigned to each student)

| Student | Patterns | Main classes |
|---|---|---|
| Erasyl | Builder, Strategy | `StudyPlanBuilder`, `ScheduleStrategy`, `PriorityStrategy`, `ShortestStrategy` |
| Arsen | Factory Method, Decorator | `TaskCreator`, `ReadingTaskCreator`, `PracticeTaskCreator`, `SessionText`, `BreakDecorator`, `TipDecorator` |
| Abylaikhan | Observer, Facade | `PlanObserver`, `ActivityHistory`, `StudyFacade` |
| Adilzhan | Adapter, Bridge | `LegacyCsvAdapter`, `OldCsvReader`, `StudyReport`, `SummaryReport`, `DetailedReport`, `ReportFormat` |

These are **assigned review and implementation responsibilities**. Every student must independently understand, revise, test and defend their two patterns. Do not claim that someone personally coded a part until they have actually done the work.

## Source structure

```text
src/main/java/kz/studysprint/
  App.java                    main entry point
  model/                      three related domain entities
  ui/                         Swing interface and task dialog
  patterns/builder/           Builder
  patterns/factory/           Factory Method
  patterns/strategy/          Strategy
  patterns/decorator/         Decorator
  patterns/observer/          Observer
  patterns/adapter/           Adapter
  patterns/bridge/            Bridge
  patterns/facade/            Facade
  service/                    schedule business logic
  storage/                    file save/load
src/test/java/kz/studysprint/  automatic tests and screenshot tool
samples/                      example CSV
images/                       screenshot taken from working GUI
docs/                         diagrams, test plan, team tasks, defense notes
```

## Design choices and limitations

The GUI is deliberately simple (Swing, one window) and uses clear Java code instead of frameworks. Data is local and single-user. The application does not send emails, sync between computers, or integrate with university systems. It uses a simple CSV format and cannot import quoted fields containing commas. Changes to tasks or schedule settings clear the old schedule to prevent stale results. Exported reports are static copies of the current plan.

## Submission checklist

- [x] Java source code, runnable desktop GUI, sample data
- [x] 8 used and demonstrated patterns, including all three pattern categories
- [x] 22 automatic tests, 8+ documented scenarios
- [x] PDF report, real GUI screenshot, class and sequence diagrams
- [x] README and beginner-friendly run/test scripts
- [ ] **Students:** create a real GitHub/GitLab repository, make and push individual genuine commits, and place its link in the report
- [ ] **Students:** verify contributions and update the report's roles to match real work
- [ ] **Students:** show weekly progress to instructor
- [ ] **Students:** practice individual defense including a small code change
- [ ] **Students:** follow course policy and accurately disclose AI/external assistance

### Academic integrity

The course allows AI for clarification, syntax, and examples but prohibits replacing independent development. This archive is a working **study starter**, prepared with AI assistance. Each person must personally study, modify, check and defend their own implementation, and report that assistance truthfully. A score of 95+ cannot be guaranteed, especially because **30/100** points depend on individual live defense.
