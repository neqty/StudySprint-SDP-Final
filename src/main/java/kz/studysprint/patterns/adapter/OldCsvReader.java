package kz.studysprint.patterns.adapter;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

// Simulated older data reader: only gives raw String arrays.
public class OldCsvReader {
    public List<String[]> readRows(Path file) throws IOException {
        List<String[]> rows = new ArrayList<>();
        for (String line : Files.readAllLines(file)) {
            if (!line.isBlank() && !line.trim().startsWith("#")) rows.add(line.split(",", -1));
        }
        return rows;
    }
}
