package org.undo;


import javax.swing.*;

/**
 * The lunch applet presents the Lunch button, which, when invoked
 * lunches the main frame
 *
 * @author Tomer Meshorer
 */
public class Launcher extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Launcher().init();
        });
    }

    //Initialize the applet
    public void init() {
        // set look & feel
        String laf = UIManager.getSystemLookAndFeelClassName();
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException exc) {
            System.err.println("Warning: UnsupportedLookAndFeel: " + laf);
        } catch (Exception exc) {
            System.err.println("Error loading " + laf + ": " + exc);
        }

        UndoPanel app = new UndoPanel();
        JFrame frame = new JFrame();
        frame.getContentPane().add(app);
        frame.setSize(600, 300);
        frame.setVisible(true);

    }

    //Start the applet
    public void start() {
    }

    //Stop the applet
    public void stop() {
    }

    //Destroy the applet
    public void destroy() {
    }


}
