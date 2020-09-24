package verification_window;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import jiconfont.icons.font_awesome.FontAwesome;

import java.awt.*;
import java.awt.event.*;
import java.awt.Color;

import spgui.SPDialog;
import spgui.SPWindow;
import spgui.componenet.Button;
import spgui.componenet.UploadArea;

public class GUI {
    SPWindow window = new SPWindow(800, 600);

    Border bottom = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black);
    Border empty = new EmptyBorder(0, 2, 5, 2);
    Border border = new CompoundBorder(bottom, empty);

    private JTextField ownerID;
    private JTextField certificateID;
    private Button upload_btn;
    private JLabel dialog_txt;
    private JLabel dialog_txt2;

    private Button dialog_yes_btn;
    private Button dialog_no_btn;

    private SPDialog dialog;
    private UploadArea uploadArea;

    private ImageIcon tittlebar_icon = new ImageIcon(getClass().getResource("/verification_window/res/BigGrayBao.png"));
    private ImageIcon upload_btn_icon = new ImageIcon(getClass().getResource("/verification_window/res/bao.png"));

    public void show() {
        window.repaint();
        window.windowContent.repaint();
        window.setVisible(true);
    }

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

        /****************************
         * A uploadArea to add file *
         ****************************/
        uploadArea = new UploadArea();
        uploadArea.setBounds(150, 260, 450, 450);
        window.addi(uploadArea);

        /************************************
         * A button to upload certification *
         ************************************/
        upload_btn = new Button(50, FontAwesome.CHECK, new Color(240, 50, 150), "Verification");
        upload_btn.setBounds(400, 300, 500, 500);
        upload_btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                dialog = new SPDialog(350, 200);
                dialog.setTitle("Bao");
                dialog.setIcon(tittlebar_icon.getImage());
                dialog.setLocationRelativeTo(window);
                dialog.setBackgroundColor(new Color(255, 44, 140, 240));

                dialog_yes_btn = new Button(20, FontAwesome.CHECK_CIRCLE, new Color(255, 255, 255), "Yes");
                dialog_yes_btn.setBounds(60, 110, 100, 40);
                dialog_yes_btn.setVisible(true);
                dialog_yes_btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("Yes");
                        dialog.setVisible(false);
                    }
                });
                dialog_no_btn = new Button(20, FontAwesome.TIMES_CIRCLE, new Color(255, 255, 255), " No ");
                dialog_no_btn.setBounds(200, 110, 100, 40);
                dialog_no_btn.setVisible(true);
                dialog_no_btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("No");
                        dialog.setVisible(false);
                    }
                });
                dialog_txt = new JLabel("Are you sure to");
                dialog_txt2 = new JLabel("verification this certification?");
                dialog_txt.setBounds(110, 30, 400, 30);
                dialog_txt2.setBounds(55, 55, 400, 30);
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
        window.addi(upload_btn);
    }
}
