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
        dialog = new SPDialog(512, 512);
        dialog.setTitleBarColor(new Color(0, 0, 0, 0));
        dialog.setBackgroundColor(new Color(0, 0, 0, 0));
        dialog.setLocationRelativeTo(null);
        dialog.setIcon(null);
        dialog.setTitle("");

        JLabel label = new JLabel("", new ImageIcon(getClass().getResource("/upload_window/res/startup.gif")),
                JLabel.CENTER);
        label.setBounds(0, 0, 512, 512);
        label.setVisible(true);

        dialog.addi(label);
        // dialog.setVisible(true);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        dialog.setVisible(false);
        dialog.dispose();

    }
}
