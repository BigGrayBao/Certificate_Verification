package verification_window;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;

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
    private JLabel ownerName_info_txt;
    private JLabel ownerID_info_txt;
    private JLabel certificateID_info_txt;
    private JLabel issuer_info_txt;
    private JLabel vaild_period_info_txt;
    private JLabel fail_txt;

    private Button dialog_yes_btn;
    private Button dialog_no_btn;
    private Button sure_btn;
    private Button fail_check_btn;

    private SPDialog dialog;
    private SPDialog certificate_info;
    private SPDialog verification_fail;
    private UploadArea uploadArea;

    private LookupCertification lookupCertification;

    private ImageIcon tittlebar_icon = new ImageIcon(getClass().getResource("/verification_window/res/BigGrayBao.png"));

    // private ImageIcon upload_btn_icon = new
    // ImageIcon(getClass().getResource("/verification_window/res/bao.png"));

    private String result = "{\"certificateID\":\"5\",\"issuer\":\"Tom\",\"ownerID\":\"yellow\",\"ownerName\":\"asset13\",\"vaildPeriod\":\"1300\"}";

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
        ownerID.setVisible(false);
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
        uploadArea = new UploadArea(250, 200, 84, 24, 24);
        uploadArea.setBounds(100, 260, 450, 450);
        window.addi(uploadArea);

        certificate_info = new SPDialog(535, 400);
        certificate_info.setTitle("Bao");
        certificate_info.setIcon(tittlebar_icon.getImage());
        certificate_info.setBackgroundColor(new Color(255, 44, 140, 240));

        verification_fail = new SPDialog(350, 200);
        verification_fail.setTitle("Bao");
        verification_fail.setIcon(tittlebar_icon.getImage());
        verification_fail.setBackgroundColor(new Color(255, 44, 140, 240));

        Color pink = new Color(255, 44, 140, 240);
        Color white = new Color(255, 255, 255, 240);
        // sure_btn = new Button(20, FontAwesome.CHECK_CIRCLE, new Color(255, 255, 255),
        // "Sure");
        IconFontSwing.register(FontAwesome.getIconFont());
        Icon check_circle_pink = IconFontSwing.buildIcon(FontAwesome.CHECK_CIRCLE, 24, pink);
        Icon check_circle_white = IconFontSwing.buildIcon(FontAwesome.CHECK_CIRCLE, 24, white);
        Icon check_pink = IconFontSwing.buildIcon(FontAwesome.CHECK, 24, pink);
        Icon check_white = IconFontSwing.buildIcon(FontAwesome.CHECK, 24, white);
        Icon times_circle_pink = IconFontSwing.buildIcon(FontAwesome.TIMES_CIRCLE, 24, pink);
        Icon times_circle_white = IconFontSwing.buildIcon(FontAwesome.TIMES_CIRCLE, 24, white);

        sure_btn = new Button(120, 40, 24, null, white, "Sure", 16);
        sure_btn.setBounds(218, 300, 130, 50);
        sure_btn.setVisible(true);
        certificate_info.addi(sure_btn);
        sure_btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                certificate_info.setVisible(false);
            }
        });

        // fail_check_btn = new Button(20, FontAwesome.CHECK_CIRCLE, new Color(255, 255,
        // 255), "Sure");
        fail_check_btn = new Button(120, 40, 24, null, white, "Sure", 16);
        fail_check_btn.setBounds(125, 110, 130, 50);
        fail_check_btn.setVisible(true);
        verification_fail.addi(fail_check_btn);
        fail_check_btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                verification_fail.setVisible(false);
            }
        });

        /************************************
         * A button to upload certification *
         ************************************/
        // upload_btn = new Button(50, FontAwesome.CHECK, new Color(240, 50, 150),
        // "Verification");
        upload_btn = new Button(250, 50, 24, check_pink, pink, "Verification", 16);
        upload_btn.setBounds(450, 325, 500, 500);
        upload_btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                dialog = new SPDialog(350, 200);
                dialog.setTitle("Bao");
                dialog.setIcon(tittlebar_icon.getImage());
                dialog.setLocationRelativeTo(window);
                dialog.setBackgroundColor(new Color(255, 44, 140, 240));

                // dialog_yes_btn = new Button(20, FontAwesome.CHECK_CIRCLE, new Color(255, 255,
                // 255), "Yes");
                dialog_yes_btn = new Button(100, 40, 24, null, white, "Yes", 16);
                dialog_yes_btn.setBounds(60, 110, 110, 50);
                dialog_yes_btn.setVisible(true);
                dialog_yes_btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("Yes");
                        lookupCertification = new LookupCertification();
                        if (lookupCertification.setCertificateInfo((String) result)) {
                            System.out.println("Find the Certification");
                            dialog.setVisible(false);

                            ownerName_info_txt = new JLabel("Owner Name : " + lookupCertification.getOwnerName());
                            ownerID_info_txt = new JLabel("Owner ID : " + lookupCertification.getOwnerID());
                            certificateID_info_txt = new JLabel(
                                    "CertificateID : " + lookupCertification.getCertificateID());
                            issuer_info_txt = new JLabel("Issuer : " + lookupCertification.getIssuer());
                            vaild_period_info_txt = new JLabel(
                                    "Vaild Period : " + lookupCertification.getVaildPeriod());
                            setlabel(ownerName_info_txt, 150, 30);
                            setlabel(ownerID_info_txt, 150, 80);
                            setlabel(certificateID_info_txt, 150, 130);
                            setlabel(issuer_info_txt, 150, 180);
                            setlabel(vaild_period_info_txt, 150, 230);

                            certificate_info.setLocationRelativeTo(window);
                            certificate_info.setVisible(true);

                        } else {
                            System.out.println("Can not find the Certification");
                            dialog.setVisible(false);

                            fail_txt = new JLabel("Can not find the Certification!");
                            fail_txt.setBounds(15, 50, 400, 30);
                            fail_txt.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 25));
                            fail_txt.setVisible(true);
                            verification_fail.addi(fail_txt);

                            verification_fail.setLocationRelativeTo(window);
                            verification_fail.setVisible(true);

                        }
                    }
                });
                // dialog_no_btn = new Button(20, FontAwesome.TIMES_CIRCLE, new Color(255, 255,
                // 255), " No ");
                dialog_no_btn = new Button(100, 40, 24, null, white, "No", 16);
                dialog_no_btn.setBounds(200, 110, 110, 50);
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

    public void setlabel(JLabel label, int x, int y) {
        label.setBounds(x, y, 400, 30);
        label.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 25));
        label.setVisible(true);
        certificate_info.addi(label);
    }
}
