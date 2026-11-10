package kz.studysprint;

import kz.studysprint.patterns.facade.StudyFacade;
import kz.studysprint.ui.MainFrame;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.*;

// Only used by the team to document the real UI. Not part of the application.
public class ScreenshotMaker {
    public static void main(String[] args) throws Exception {
        Path target = Paths.get(args.length > 0 ? args[0] : "images/app_screenshot.png");
        Path temp = Files.createTempDirectory("study-screenshot-").resolve("plans.txt");
        StudyFacade app = new StudyFacade(temp);
        app.generateSchedule();
        final MainFrame[] holder = new MainFrame[1];
        SwingUtilities.invokeAndWait(() -> {
            holder[0] = new MainFrame(app);
            holder[0].setLocation(20, 20);
            holder[0].setVisible(true);
        });
        Thread.sleep(1200);
        Rectangle bounds = holder[0].getBounds();
        BufferedImage screenshot = new Robot().createScreenCapture(bounds);
        Files.createDirectories(target.toAbsolutePath().getParent());
        ImageIO.write(screenshot, "png", target.toFile());
        SwingUtilities.invokeAndWait(() -> holder[0].dispose());
        System.out.println("Screenshot saved: " + target);
    }
}
