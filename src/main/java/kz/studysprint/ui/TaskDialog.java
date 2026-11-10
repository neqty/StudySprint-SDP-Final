package kz.studysprint.ui;

import kz.studysprint.model.StudyTask;
import javax.swing.*;
import java.awt.*;

// A small reusable form for both Add and Edit.
public class TaskDialog {
    public static class Values {
        public String type, title, subject, notes;
        public int minutes, priority;
    }
    public static Values show(Component parent, StudyTask old) {
        JComboBox<String> type = new JComboBox<>(new String[]{"Reading", "Practice"});
        JTextField title = new JTextField(20);
        JTextField subject = new JTextField(20);
        JSpinner minutes = new JSpinner(new SpinnerNumberModel(30, 5, 300, 5));
        JSpinner priority = new JSpinner(new SpinnerNumberModel(3, 1, 5, 1));
        JTextField notes = new JTextField(20);
        if (old != null) {
            type.setSelectedItem(old.getType()); title.setText(old.getTitle());
            subject.setText(old.getSubject()); minutes.setValue(old.getMinutes());
            priority.setValue(old.getPriority()); notes.setText(old.getNotes());
        }
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.add(new JLabel("Task type:")); panel.add(type);
        panel.add(new JLabel("Task title:")); panel.add(title);
        panel.add(new JLabel("Subject:")); panel.add(subject);
        panel.add(new JLabel("Minutes (5-300):")); panel.add(minutes);
        panel.add(new JLabel("Priority (1-5):")); panel.add(priority);
        panel.add(new JLabel("Notes:")); panel.add(notes);
        int answer = JOptionPane.showConfirmDialog(parent, panel,
            old == null ? "Add task" : "Edit task", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (answer != JOptionPane.OK_OPTION) return null;
        Values v = new Values();
        v.type = (String) type.getSelectedItem(); v.title = title.getText();
        v.subject = subject.getText(); v.minutes = (Integer) minutes.getValue();
        v.priority = (Integer) priority.getValue(); v.notes = notes.getText();
        return v;
    }
}
