package upload_window;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import org.json.JSONArray;
import org.json.JSONObject;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;

import java.awt.*;
import java.awt.event.*;
import java.awt.Color;

import java.util.ArrayList;

import spgui.SPDialog;
import spgui.SPWindow;
import spgui.componenet.Button;
import spgui.componenet.ToggleBtn;
import spgui.componenet.UploadArea;

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

    private JLabel open_vaild_period_txt;
    private JLabel dialog_txt;
    private JLabel dialog_txt2;
    private JLabel table;
    private JLabel upload_txt_label;
    private JLabel upload_certificate_btn;
    private JLabel upload_json_btn;
    private JLabel add_new_data_btn;

    private ToggleBtn vaild_period_btn;
    private Button dialog_yes_btn;
    private Button dialog_no_btn;
    private Button upload_btn;

    private SPDialog dialog;
    private SPDialog load_json_dialog;
    private SPDialog add_new_data_dialog;
    private SPDialog upload_certificate_dialog;
    private UploadCertification upload;
    private UploadArea uploadArea;
    private ReadJson readJson = new ReadJson();
    private String path;
    private JSONArray dataArray;

    private ImageIcon tittlebar_icon = createImageIcon("/upload_window/res/BigGrayBao.png", null);
    private ImageIcon table_icon = createImageIcon("/upload_window/res/table.png", null);
    private ImageIcon upload_txt = createImageIcon("/upload_window/res/upload_txt.png", null);
    private ImageIcon upload_certificate = createImageIcon("/upload_window/res/upload_certificate.png", null);
    private ImageIcon upload_certificate_big = createImageIcon("/upload_window/res/upload_certificate_entered.png",
            null);
    private ImageIcon upload_json = createImageIcon("/upload_window/res/upload_json.png", null);
    private ImageIcon upload_json_big = createImageIcon("/upload_window/res/upload_json_entered.png", null);
    private ImageIcon add_new_data = createImageIcon("/upload_window/res/add_new_data.png", null);
    private ImageIcon add_new_data_big = createImageIcon("/upload_window/res/add_new_data_entered.png", null);
    // private ImageIcon upload_btn_icon = new
    // ImageIcon(getClass().getResource("/upload_window/res/bao.png"));

    private ArrayList<String> certification = new ArrayList<>();
    private DataTable dataTable = new DataTable();
    JSONObject bao = new JSONObject(
            "{\"certificateID\":\"5\",\"issuer\":\"Tom\",\"ownerID\":\"yellow\",\"ownerName\":\"asset13\",\"vaildPeriod\":\"1300\"}");

    public void show() {
        window.repaint();
        window.windowContent.repaint();
        window.setVisible(true);
    }

    public void run() {
        window.setVisible(false);
        window.setBackgroundColor(new Color(122, 122, 122, 255));
        window.setLocationRelativeTo(null);
        window.setTitleBarColor(new Color(255, 0, 128, 200));
        window.setResizable(false);
        window.setTitle("BaoGrayBao");
        window.setIcon(tittlebar_icon.getImage());

        // A data table to view certificate info
        table = new JLabel("", table_icon, JLabel.CENTER);
        table.setBounds(0, -30, 800, 600);
        table.setVisible(true);
        window.addi(table, 200);

        // Text
        upload_txt_label = new JLabel("", upload_txt, JLabel.CENTER);
        upload_txt_label.setBounds(0, -30, 800, 600);
        upload_txt_label.setVisible(true);
        window.addi(upload_txt_label);

        upload_certificate_btn = new JLabel("", upload_certificate, JLabel.CENTER);
        upload_certificate_btn.setBounds(260, 75, 180, 50);
        upload_certificate_btn.setVisible(true);
        upload_certificate_btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                upload_certificate_btn.setIcon(upload_certificate_big);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                upload_certificate_btn.setIcon(upload_certificate);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                upload_certificate_btn.setIcon(upload_certificate);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                upload_certificate_btn.setIcon(upload_certificate_big);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                upload_certificate_dialog = new SPDialog(350, 200);
                upload_certificate_dialog.setLocationRelativeTo(window);
                upload_certificate_dialog.setVisible(true);
            }
        });
        window.addi(upload_certificate_btn, 200);

        add_new_data_btn = new JLabel("", add_new_data, JLabel.CENTER);
        add_new_data_btn.setBounds(430, 73, 180, 50);
        add_new_data_btn.setVisible(true);
        add_new_data_btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                add_new_data_btn.setIcon(add_new_data_big);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                add_new_data_btn.setIcon(add_new_data);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                add_new_data_btn.setIcon(add_new_data);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                add_new_data_btn.setIcon(add_new_data_big);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                dataTable.addData(bao);
                // add_new_data_dialog = new SPDialog(350, 200);
                // add_new_data_dialog.setLocationRelativeTo(window);
                // add_new_data_dialog.setVisible(true);
            }
        });
        window.addi(add_new_data_btn, 300);

        upload_json_btn = new JLabel("", upload_json, JLabel.CENTER);
        upload_json_btn.setBounds(600, 75, 180, 50);
        upload_json_btn.setVisible(true);
        upload_json_btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                upload_json_btn.setIcon(upload_json_big);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                upload_json_btn.setIcon(upload_json);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                upload_json_btn.setIcon(upload_json);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                upload_json_btn.setIcon(upload_json_big);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                load_json_dialog = new SPDialog(350, 200);
                load_json_dialog.setLocationRelativeTo(window);
                load_json_dialog.setVisible(true);
            }
        });
        window.addi(upload_json_btn, 200);

        // JSONObject bao = new JSONObject(
        // "{\"certificateID\":\"5\",\"issuer\":\"Tom\",\"ownerID\":\"yellow\",\"ownerName\":\"asset13\",\"vaildPeriod\":\"1300\"}");

        // for (int i = 0; i < 10; i++)
        // dataTable.addData(bao);
        dataTable.setBounds(50, 160, 800, 600);
        window.addi(dataTable, Integer.valueOf(300));

        open_vaild_period_txt = new JLabel();
        open_vaild_period_txt.setText("open Vaild Period");
        open_vaild_period_txt.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 18));
        open_vaild_period_txt.setBounds(450, 243, 150, 100);
        open_vaild_period_txt.setVisible(true);
        // window.addi(open_vaild_period_txt);

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
        // window.addi(ownerName);

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
        // window.addi(ownerID);

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
        // window.addi(certificateID);

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
        // window.addi(Issuer);

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
        // window.addi(vaild_period);

        /****************************
         * A uploadArea to add file *
         ****************************/
        uploadArea = new UploadArea(250, 150, 54, 28, 24);
        uploadArea.setBounds(100, 360, 450, 450);
        // window.addi(uploadArea);

        Color pink = new Color(255, 44, 140, 240);
        Color white = new Color(255, 255, 255, 240);

        IconFontSwing.register(FontAwesome.getIconFont());
        Icon check_circle_pink = IconFontSwing.buildIcon(FontAwesome.CHECK_CIRCLE, 24, pink);
        Icon check_circle_white = IconFontSwing.buildIcon(FontAwesome.CHECK_CIRCLE, 24, white);
        Icon check_pink = IconFontSwing.buildIcon(FontAwesome.CHECK, 32, pink);
        Icon check_white = IconFontSwing.buildIcon(FontAwesome.CHECK, 24, white);
        Icon times_circle_pink = IconFontSwing.buildIcon(FontAwesome.TIMES_CIRCLE, 24, pink);
        Icon times_circle_white = IconFontSwing.buildIcon(FontAwesome.TIMES_CIRCLE, 24, white);

        /************************************
         * A button to upload certification *
         ************************************/
        upload_btn = new Button(250, 50, 32, check_pink, pink, "Upload", 16);
        upload_btn.setBounds(450, 380, 500, 500);
        upload_btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                upload = new UploadCertification();
                upload.setCertification(certification);
                upload.printCertification();

                // upload data
                if (!uploadArea.getPath().isEmpty()) {
                    path = readJson.ReadJson(uploadArea.getPath().get(0));
                    dataArray = new JSONArray(path);
                    System.out.println(dataArray.getJSONObject(0).getString("ownerName"));
                    System.out.println(dataArray.getJSONObject(0).getString("ownerID"));
                    System.out.println(dataArray.getJSONObject(0).getString("certificateID"));
                    System.out.println(dataArray.getJSONObject(0).getString("issuer"));
                    System.out.println(dataArray.getJSONObject(0).getString("vaildPeriod"));

                }

                dialog = new SPDialog(350, 200);
                dialog.setTitle("Bao");
                dialog.setIcon(tittlebar_icon.getImage());
                dialog.setLocationRelativeTo(window);
                dialog.setBackgroundColor(new Color(255, 44, 140, 240));

                dialog_yes_btn = new Button(100, 40, 24, null, white, "Yes", 16);
                dialog_yes_btn.setBounds(60, 110, 110, 50);
                dialog_yes_btn.setVisible(true);
                dialog_yes_btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("Yes");
                        dialog.setVisible(false);
                    }
                });
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
        window.addi(upload_btn);
    }

    protected ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
