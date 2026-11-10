package kz.studysprint.patterns.adapter;

import kz.studysprint.model.StudyTask;
import kz.studysprint.patterns.factory.TaskCreators;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

// Converts the old columns (title,subject,minutes,priority,type) into our model.
public class LegacyCsvAdapter implements TaskImporter {
    private final OldCsvReader oldReader;
    public LegacyCsvAdapter(OldCsvReader oldReader) { this.oldReader = oldReader; }
    @Override public List<StudyTask> importTasks(Path file) throws IOException {
        List<StudyTask> results = new ArrayList<>();
        int row = 0;
        for (String[] cells : oldReader.readRows(file)) {
            row++;
            try {
                if (cells.length != 5) throw new IllegalArgumentException("5 columns required");
                int minutes = Integer.parseInt(cells[2].trim());
                int priority = Integer.parseInt(cells[3].trim());
                results.add(TaskCreators.forType(cells[4].trim())
                    .create(cells[0], cells[1], minutes, priority, "Imported from CSV"));
            } catch (IllegalArgumentException e) {
                throw new IOException("Invalid CSV row " + row + ": " + e.getMessage(), e);
            }
        }
        if (results.isEmpty()) throw new IOException("CSV file has no tasks.");
        return results;
    }
}
