package upload_window;

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

import java.util.ArrayList;

import spgui.SPDialog;
import spgui.SPWindow;
import spgui.componenet.ToggleBtn;

public class GUI {
    SPWindow window = new SPWindow(800, 600);

    Border bottom = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black);
    Border empty = new EmptyBorder(0, 2, 5, 2);
    Border border = new CompoundBorder(bottom, empty);

    private JTextField ownerName;
    private JTextField ownerID;
    private JTextField certificateID;
    private JTextField Issuer;
    private JTextField vaild_period;
    private ToggleBtn vaild_period_btn;
    private JTextField none;
    private ToggleBtn none_btn;
    private JLabel open_vaild_period_txt;
    private JLabel open_none_txt;
    private JLabel upload_btn;
    private JLabel dialog_txt;
    private JLabel dialog_txt2;

    private JButton dialog_yes_btn;
    private JButton dialog_yes_btn_pressed;
    private JButton dialog_no_btn;
    private JButton dialog_no_btn_pressed;

    private SPDialog dialog;
    private UploadCertification upload;

    private ImageIcon tittlebar_icon = new ImageIcon(getClass().getResource("/upload_window/res/BigGrayBao.png"));
    private ImageIcon upload_btn_icon = new ImageIcon(getClass().getResource("/upload_window/res/bao.png"));

    private ArrayList<String> certification = new ArrayList<>();

    public void run() {

        window.setBackgroundColor(new Color(255, 255, 255, 255));
        window.setLocationRelativeTo(null);
        window.setTitleBarColor(new Color(255, 44, 140, 220));
        window.setResizable(false);
        window.setTitle("BaoGrayBao");
        window.setIcon(tittlebar_icon.getImage());
        window.setVisible(true);

        open_vaild_period_txt = new JLabel();
        open_vaild_period_txt.setText("open Vaild Period");
        open_vaild_period_txt.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 18));
        open_vaild_period_txt.setBounds(450, 243, 150, 100);
        open_vaild_period_txt.setVisible(true);
        window.addi(open_vaild_period_txt);

        /******************************************
         * A toggle button to enable vaild preiod *
         ******************************************/
        vaild_period_btn = new ToggleBtn(50, 30);
        vaild_period_btn.setBounds(615, 260, 150, 100);
        vaild_period_btn.setColor(new Color(255, 44, 140, 200), new Color(255, 44, 140, 255),
                new Color(255, 44, 140, 100));
        vaild_period_btn.setVisible(true);
        window.addi(vaild_period_btn);
        vaild_period_btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                boolean status = !vaild_period_btn.getStatus();
                vaild_period.setVisible(status);
            }
        });

        open_none_txt = new JLabel();
        open_none_txt.setText("open None");
        open_none_txt.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 18));
        open_none_txt.setBounds(450, 343, 150, 100);
        open_none_txt.setVisible(true);
        window.addi(open_none_txt);

        /**********************************
         * A toggle button to enable none *
         **********************************/
        none_btn = new ToggleBtn(50, 30);
        none_btn.setBounds(615, 360, 150, 100);
        none_btn.setColor(new Color(255, 44, 140, 200), new Color(255, 44, 140, 255), new Color(255, 44, 140, 100));
        none_btn.setVisible(true);
        window.addi(none_btn);
        none_btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                boolean status = !none_btn.getStatus();
                none.setVisible(status);
            }
        });

        /***************************
         * TextField for ownerName *
         ***************************/
        ownerName = new JTextField();
        ownerName.setBounds(100, 70, 250, 50);
        ownerName.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 30));
        ownerName.addFocusListener(new JTextFieldHintListener(ownerName, "ownerName"));
        ownerName.setOpaque(false);
        ownerName.setBorder(border);
        ownerName.enableInputMethods(false);
        window.addi(ownerName);

        /*************************
         * TextField for ownerID *
         *************************/
        ownerID = new JTextField();
        ownerID.setBounds(100, 170, 250, 50);
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
        certificateID.setBounds(450, 70, 250, 50);
        certificateID.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 30));
        certificateID.addFocusListener(new JTextFieldHintListener(certificateID, "certificateID"));
        certificateID.setOpaque(false);
        certificateID.setBorder(border);
        certificateID.enableInputMethods(false);
        window.addi(certificateID);

        /************************
         * TextField for Issuer *
         ************************/
        Issuer = new JTextField();
        Issuer.setBounds(450, 170, 250, 50);
        Issuer.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 30));
        Issuer.addFocusListener(new JTextFieldHintListener(Issuer, "Issuer"));
        Issuer.setOpaque(false);
        Issuer.setBorder(border);
        Issuer.enableInputMethods(false);
        window.addi(Issuer);

        /******************************
         * TextField for Vaild period *
         ******************************/
        vaild_period = new JTextField();
        vaild_period.setBounds(100, 270, 250, 50);
        vaild_period.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 30));
        vaild_period.addFocusListener(new JTextFieldHintListener(vaild_period, "Vaild period"));
        vaild_period.setOpaque(false);
        vaild_period.setBorder(border);
        vaild_period.setVisible(false);
        vaild_period.enableInputMethods(false);
        window.addi(vaild_period);

        /******************************
         * TextField for none *
         ******************************/
        none = new JTextField();
        none.setBounds(100, 370, 250, 50);
        none.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 30));
        none.addFocusListener(new JTextFieldHintListener(none, "none"));
        none.setOpaque(false);
        none.setBorder(border);
        none.setVisible(false);
        none.enableInputMethods(false);
        window.addi(none);
        // System.out.println(none.getText().equals("none") ? "" : none.getText());

        /************************************
         * A button to upload certification *
         ************************************/
        upload_btn = new JLabel();
        upload_btn.setIcon(upload_btn_icon);
        upload_btn.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 30));
        upload_btn.setBounds(450, 450, 250, 80);
        upload_btn.setVisible(true);
        window.addi(upload_btn, 2000);
        upload_btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                upload = new UploadCertification();
                upload.setCertification(certification);
                upload.printCertification();

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
