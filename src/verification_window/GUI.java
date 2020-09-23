package verification_window;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.awt.Color;

import spgui.SPDialog;
import spgui.SPWindow;

public class GUI {
    SPWindow window = new SPWindow(800, 600);

    Border bottom = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black);
    Border empty = new EmptyBorder(0, 2, 5, 2);
    Border border = new CompoundBorder(bottom, empty);

    private JTextField ownerID;
    private JTextField certificateID;
    private JLabel upload_btn;
    private JLabel dialog_txt;
    private JLabel dialog_txt2;

    private JButton dialog_yes_btn;
    private JButton dialog_yes_btn_pressed;
    private JButton dialog_no_btn;
    private JButton dialog_no_btn_pressed;

    private SPDialog dialog;

    private ImageIcon tittlebar_icon = new ImageIcon(getClass().getResource("/verification_window/res/BigGrayBao.png"));
    private ImageIcon upload_btn_icon = new ImageIcon(getClass().getResource("/verification_window/res/bao.png"));

    public void run() {

        window.setBackgroundColor(new Color(255, 255, 255, 255));
        window.setLocationRelativeTo(null);
        window.setTitleBarColor(new Color(255, 44, 140, 220));
        window.setResizable(false);
        window.setTitle("BaoGrayBao");
        window.setIcon(tittlebar_icon.getImage());
        window.setVisible(true);

        /*************************
         * TextField for ownerID *
         *************************/
        ownerID = new JTextField();
        ownerID.setBounds(100, 120, 250, 50);
        ownerID.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 30));
        ownerID.addFocusListener(new JTextFieldHintListener(ownerID, "ownerID"));
        ownerID.setOpaque(false);
        ownerID.setBorder(border);
        ownerID.enableInputMethods(false);
        window.addi(ownerID);

        /*******************************
         * TextField for certificateID *
         *******************************/
        certificateID = new JTextField();
        certificateID.setBounds(450, 120, 250, 50);
        certificateID.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 30));
        certificateID.addFocusListener(new JTextFieldHintListener(certificateID, "certificateID"));
        certificateID.setOpaque(false);
        certificateID.setBorder(border);
        certificateID.enableInputMethods(false);
        window.addi(certificateID);

        /************************************
         * A button to upload certification *
         ************************************/
        upload_btn = new JLabel();
        upload_btn.setIcon(upload_btn_icon);
        upload_btn.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 30));
        upload_btn.setBounds(275, 300, 250, 80);
        upload_btn.setVisible(true);
        window.addi(upload_btn, 2000);
        upload_btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                dialog = new SPDialog(350, 200);
                dialog.setTitle("Bao");
                dialog.setIcon(tittlebar_icon.getImage());
                dialog.setLocationRelativeTo(window);
                dialog.setBackgroundColor(new Color(255, 44, 140, 240));

                dialog_yes_btn = new JButton();
                dialog_yes_btn.setBounds(50, 110, 100, 40);
                dialog_yes_btn.setText("Yes");
                dialog_yes_btn.setVisible(true);
                dialog_yes_btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("Yes");
                        dialog.setVisible(false);
                    }
                });
                dialog_no_btn = new JButton();
                dialog_no_btn.setBounds(200, 110, 100, 40);
                dialog_no_btn.setText("No");
                dialog_no_btn.setVisible(true);
                dialog_no_btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("No");
                        dialog.setVisible(false);
                    }
                });
                dialog_txt = new JLabel("You can not modify after upload.");
                dialog_txt2 = new JLabel("Do you want to upload?");
                dialog_txt.setBounds(35, 30, 400, 30);
                dialog_txt2.setBounds(70, 55, 400, 30);
                dialog_txt.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 20));
                dialog_txt2.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 20));
                dialog_txt.setVisible(true);
                dialog_txt2.setVisible(true);

                dialog.addi(dialog_yes_btn);
                dialog.addi(dialog_no_btn);
                dialog.addi(dialog_txt);
                dialog.addi(dialog_txt2);
                dialog.setVisible(true);
            }
        });

    }
}
