package upload_window;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import spgui.SPDialog;

public class StartupWindow {
    SPDialog dialog;

    public StartupWindow() {

    }

    public void start() {
        dialog = new SPDialog(794, 615);
        dialog.setModal(false);
        dialog.setTitleBarColor(new Color(0, 0, 0, 0));
        dialog.setBackgroundColor(new Color(0, 0, 0, 0));
        dialog.setLocationRelativeTo(null);
        dialog.setIcon(null);
        dialog.setTitle("");

        JLabel label = new JLabel("", new ImageIcon(getClass().getResource("/upload_window/res/startup.gif")),
                JLabel.CENTER);
        label.setBounds(0, 0, 794, 615);
        label.setVisible(true);
        dialog.addi(label);
        dialog.setVisible(true);
    }

    public void close() {
        dialog.setVisible(false);
    }
}
