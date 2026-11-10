# Test scenarios

Test runner: `test.bat` / `./test.sh`. Test implementation: `src/test/java/kz/studysprint/StudySprintTests.java`.

The entries marked **PASS** are backed by the 22 automatic checks saved in `docs/test_output.txt`.

| # | Scenario | Input / steps | Expected | Actual |
|---|---|---|---|---|
| 1 | First launch and sample data | New empty data path | One plan with 3 sample tasks | PASS (#1) |
| 2 | Create a study plan | Name = `My tests` | Plan is created and selected | PASS (#2) |
| 3 | Add two types of task | Reading 60m and Practice 20m | Two different task types and tips | PASS (#3-4) |
| 4 | Switch algorithms | Priority values 5 and 1, times 60 and 20 | Priority picks 60m first; shortest picks 20m first | PASS (#5-6) |
| 5 | Enable decorators | 2 sessions, breaks on, tips on | +10 minutes in total and tips shown | PASS (#7-8) |
| 6 | Trigger data event | Generate schedule | Activity observer gets notification | PASS (#9) |
| 7 | Mark task complete | Mark first task done | Task done, outdated schedule cleared | PASS (#10) |
| 8 | Save and reload | Reopen same file path | Task completion persists | PASS (#11) |
| 9 | Export report | Detailed HTML and Detailed TXT | Proper HTML and TXT contents | PASS (#12, #21) |
| 10 | Invalid title | Empty title | Error, no new task | PASS (#13) |
| 11 | Invalid duration | 1 minute | Error (allowed 5-300) | PASS (#14) |
| 12 | Invalid priority | Priority 8 | Error (allowed 1-5) | PASS (#15) |
| 13 | Invalid plan name | Empty name | Error | PASS (#16) |
| 14 | Import legacy CSV | Two valid CSV rows | Two valid tasks added | PASS (#17) |
| 15 | Invalid CSV | Negative time | Error; no partial addition | PASS (#18) |
| 16 | Edit and remove task | Rename, change type, then remove | Correct updated values; item deleted | PASS (#19-20, #22) |

## GUI verification

The Swing window was launched in an X virtual display, the example schedule was generated, and `images/app_screenshot.png` was captured from the running application. Live GUI button-by-button checks should be performed by each student locally before submission. This matters because the automated tests focus on business logic, not UI automation.

## Additional manual checks before defense

- Change a plan's name, switch plans, delete a plan, and verify at least one remains.
- Add a new task with the GUI, select the row, edit it and mark it done.
- Close the app and restart; ensure plans/tasks survive.
- Change schedule method and options; ensure new output reflects them.
- Show the status bar, report preview, saved file and Activity tab.
- Try actions with no selected task; confirm error dialog appears.
