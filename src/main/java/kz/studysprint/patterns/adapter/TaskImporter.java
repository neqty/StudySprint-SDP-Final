package kz.studysprint.patterns.adapter;

import kz.studysprint.model.StudyTask;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

// ADAPTER target interface: the rest of our app expects ready-to-use StudyTasks.
public interface TaskImporter { List<StudyTask> importTasks(Path file) throws IOException; }
