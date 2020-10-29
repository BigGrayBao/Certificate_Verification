import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import spgui.SPWindow;

import java.awt.*;

import upload_window.GUI;
// import verification_window.GUI;
import upload_window.StartupWindow;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                System.setProperty("sun.java2d.opengl", "true");
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                try {
                    String filepath = getClass().getResource("/upload_window/res/jf-openhuninn-1.0.ttf").getFile();
                    // String filepath =
                    // getClass().getResourceAsStream("/upload_window/res/jf-openhuninn-1.0.ttf");
                    System.out.println(filepath);
                    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("./jf-openhuninn-1.0.ttf")));
                    // ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,
                    // getClass().getResourceAsStream("/upload_window/res/jf-openhuninn-1.0.ttf")));
                    // ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(filepath)));
                } catch (IOException | FontFormatException e) {
                    e.printStackTrace();
                }

                System.out.println("Welcome to Baolockchain");
                GUI gui = new GUI();
                gui.run();
                StartupWindow startupWindow = new StartupWindow();
                startupWindow.start();
                gui.show();
            }
        });
    }
}