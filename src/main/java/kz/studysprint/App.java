package kz.studysprint;

import kz.studysprint.patterns.facade.StudyFacade;
import kz.studysprint.ui.MainFrame;
import javax.swing.*;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                MainFrame frame = new MainFrame(new StudyFacade(Paths.get("data", "plans.txt")));
                frame.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Cannot open Study Sprint: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}
