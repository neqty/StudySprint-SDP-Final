# Exact assignment checklist - FINAL PROJECT.docx

The following checklist uses the original assignment's sections. **READY** means the archive contains relevant code/evidence. **STUDENT ACTION** must be completed by the four students; the archive cannot substitute their actual contribution or assessment.

## 1. Task and complete user journey

- READY - A student can create/select a plan, configure tasks, generate sessions, preview and save results.
- READY - The problem is explained in the report's Introduction.

## 3. Common functional requirements

1. READY - **3 related entities:** StudyPlan, StudyTask, StudySession.
2. READY - **5+ scenarios:** 8 scenarios documented and at least 8 are testable through UI.
3. READY - **Create/view/edit/delete:** plan and task operations exist in MainFrame and StudyFacade; completion can be undone.
4. READY - **Interchangeable behavior:** PriorityStrategy and ShortestStrategy switch by user setting.
5. READY - **Event-driven reaction:** PlanObserver messages refresh GUI and ActivityHistory.
6. READY - **Persistence after restart:** PlanRepository saves/loads plans, tasks, sessions and settings.
7. READY - **Final output:** Summary/Detailed TXT or HTML report, preview and save.
8. READY - **Validation/error handling:** required fields, time and priority ranges, invalid CSV, no unfinished tasks, last-plan delete.
- READY - **Sample data:** 3 tasks loaded on first run; sample CSV also included.

## 4. Design pattern requirements

- READY - **8 distinct patterns**, more than the 6 minimum.
- READY - **Creational:** Builder, Factory Method.
- READY - **Structural:** Decorator, Facade, Adapter, Bridge.
- READY - **Behavioral:** Strategy, Observer.
- READY - Each pattern appears in live workflow and in the report table with problem, main classes, demo and assigned student.
- STUDENT ACTION - Each of four students personally implement/revise and defend at least **2 patterns**; names in this archive are proposed roles until verified.

## 5. Interface requirements

- READY - Desktop Java Swing GUI (no browser setup).
- READY - Browse main objects (plan picker/task table), create and edit data.
- READY - Configure available scheduling/decorator options.
- READY - Full journey, results, error dialogs, and saved output.
- READY - Java core application logic; actual user events call business logic.

## 6. Code quality and testing

- READY - UI, models, services/patterns, storage separated.
- READY - Meaningful names and small interfaces; exceptions validated.
- READY - 16 documented test scenarios with inputs/expected/actual status, 22 automated checks, 3+ automatic business-logic tests.
- READY - Coverage includes successful journey, alternative algorithms, event, persistence and 3+ invalid cases.
- STUDENT ACTION - Manual checks on the actual laptop and screenshots of final student-made changes.

## 7. Three-week development plan

- READY - docs/WEEKLY_PLAN.md describes all 3 stages.
- STUDENT ACTION - Show real progress at the end of each week and collect instructor feedback.
- STUDENT ACTION - Confirm Week 9 submission and Week 10 defense dates with instructor; meet the course deadline.

## 8. Submission

- READY - Source ZIP archive and Java files.
- READY - README with JDK requirements, launch/test commands, examples and demo steps.
- READY - REPORT.pdf: Introduction, Main Body with scenarios, architecture, pattern table, UML, real screenshot, test results and proposed individual responsibilities; Conclusion and Future Work.
- READY - Java code excerpts explaining all 8 patterns included in the PDF.
- READY - UML class and sequence diagrams both in PDF and editable `.mmd` sources.
- STUDENT ACTION - Real public GitHub/GitLab repository link with genuine multi-member development history; replace report placeholder.
- STUDENT ACTION - Submit ZIP, PDF and repository link through Moodle.

## 9. Defense

- READY - docs/DEFENSE_GUIDE.md has two patterns and a live-change rehearsal for each student.
- STUDENT ACTION - Each student demonstrates code, patterns, limitations and interactions, understands whole app, and successfully makes the instructor's requested small code change.

## 10. Assessment

| Assessment category | Maximum | Status |
|---|---:|---|
| Functionality and complete user scenarios | 20 | Ready to demonstrate |
| Correctness / justification / integration of six patterns | 30 | 8 examples implemented; explanation and live demonstration required |
| Architecture / SOLID / readability | 10 | Ready for inspection |
| UI usability and functionality | 5 | Working Swing UI, captured screenshot |
| Documentation / testing / reproducible setup / Git | 5 | Docs + tests ready; **Git team history pending** |
| Individual defense | 30 | **Unscored until each student defends** |
| **Total** | **100** | A 95+ mark cannot be guaranteed |

## 11. Independent work and AI use

- READY - AI disclosure appears in report, README and docs/AI_DISCLOSURE.md.
- STUDENT ACTION - Ensure truthful disclosure and real student work consistent with course policy. AI support must not replace the independently developed solution.
