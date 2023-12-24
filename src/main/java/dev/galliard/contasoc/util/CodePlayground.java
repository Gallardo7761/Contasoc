package dev.galliard.contasoc.util;

import java.awt.*;
import javax.swing.*;

public class CodePlayground {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new CodePlayground().makeUI();
            }
        });
    }

    public void makeUI() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setTabPlacement(JTabbedPane.LEFT);
        JPanel panel = new JPanel(new GridLayout(0, 1));

        for (int i = 0; i < 3; i++) {
            JPanel tab = new JPanel();
            tab.setName("tab" + (i + 1));
            tab.setPreferredSize(new Dimension(400, 400));
            tabbedPane.add(tab);

            JButton button = new JButton("B" + (i + 1));
            button.setMargin(new Insets(0, 0, 0, 0));
            panel.add(button);
        }

        JFrame frame = new JFrame();
        frame.add(tabbedPane);
        frame.pack();
        Rectangle tabBounds = tabbedPane.getBoundsAt(0);

        Container glassPane = (Container) frame.getGlassPane();
        glassPane.setVisible(true);
        glassPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.NONE;
        int margin = - tabbedPane.getWidth() + (tabBounds.x + tabBounds.width);
        gbc.insets = new Insets(0, margin, 50, 0);
        gbc.anchor = GridBagConstraints.SOUTHWEST;

        panel.setPreferredSize(new Dimension((int) tabBounds.getWidth() - margin,
                panel.getPreferredSize().height));
        glassPane.add(panel, gbc);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}