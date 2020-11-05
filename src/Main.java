import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

import java.awt.*;

import upload_window.GUI;
import upload_window.StartupWindow;
// import verification_window.GUI;
// import verification_window.StartupWindow;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                System.setProperty("sun.java2d.opengl", "true");
                System.out.println("Welcome to Baolockchain");
                GUI gui = new GUI();
                gui.run();
                StartupWindow startupWindow = new StartupWindow();
                startupWindow.start();
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        startupWindow.close();
                        gui.show();
                        timer.cancel();
                    }
                }, 4700/* 3500 */, 1);
            }
        });
    }
}