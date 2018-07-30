package org.splitpane;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Simple trial with split panes and mig layout
 */
public class SplitPaneLab extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SplitPaneLab().setVisible(true);
        });
    }

    public SplitPaneLab() {

        JPanel panelLeft = new JPanel(new MigLayout(""));
        // minimum and maximum size allow to restrict dividers
        panelLeft.setMinimumSize(new Dimension(200,200));
        panelLeft.setMaximumSize(new Dimension(400,200));

        panelLeft.add(new JTextField(), "width 80%");
        panelLeft.add(new JButton("O.K."), "width 15%, wrap");
        panelLeft.add(new JTextField(), "width 80%");
        panelLeft.add(new JButton("O.K."), "width 15%, wrap");


        JPanel panelRight = new JPanel(new MigLayout(""));
        panelRight.add(new JTextField(), "width 80%");
        panelRight.add(new JButton("O.K."), "width 15%, wrap");
        panelRight.add(new JTextField(), "width 80%");
        panelRight.add(new JButton("O.K."), "width 15%, wrap");
        panelRight.setMinimumSize(new Dimension(200,200));

        JPanel panelCenter = new JPanel(new MigLayout(""));
        panelCenter.add(new JLabel("<html>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque facilisis nec nisi id lacinia. Suspendisse semper ipsum in purus interdum semper. Mauris non nunc non risus hendrerit faucibus egestas quis lectus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Praesent orci sem, aliquam in dictum et, facilisis ac ligula. Sed rutrum nisi a massa pulvinar, in tempus odio accumsan. Nam at quam congue, feugiat mauris quis, ullamcorper odio. Vestibulum finibus diam non nibh viverra maximus. Nulla commodo, sapien sit amet volutpat interdum, quam ipsum porta diam, eget suscipit leo nulla in dolor. Donec auctor suscipit dapibus. Duis porta, felis ac elementum dignissim, risus metus vestibulum eros, et auctor diam ipsum sit amet diam.</html>"), "wrap");
        panelCenter.add(new JLabel("<html>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque facilisis nec nisi id lacinia. Suspendisse semper ipsum in purus interdum semper. Mauris non nunc non risus hendrerit faucibus egestas quis lectus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Praesent orci sem, aliquam in dictum et, facilisis ac ligula. Sed rutrum nisi a massa pulvinar, in tempus odio accumsan. Nam at quam congue, feugiat mauris quis, ullamcorper odio. Vestibulum finibus diam non nibh viverra maximus. Nulla commodo, sapien sit amet volutpat interdum, quam ipsum porta diam, eget suscipit leo nulla in dolor. Donec auctor suscipit dapibus. Duis porta, felis ac elementum dignissim, risus metus vestibulum eros, et auctor diam ipsum sit amet diam.</html>"));

        JSplitPane splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelLeft, panelCenter);
        splitPane1.setOneTouchExpandable(true);
        splitPane1.setDividerLocation(200);

        JSplitPane splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitPane1, panelRight);
        splitPane2.setOneTouchExpandable(true);
        splitPane2.setDividerLocation(500);

        setContentPane(splitPane2);

        setSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
