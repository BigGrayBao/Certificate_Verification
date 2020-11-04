package upload_window;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import org.json.JSONArray;
import org.json.JSONObject;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;

import java.awt.*;
import java.awt.event.*;
import java.io.UnsupportedEncodingException;
import java.awt.Color;

import java.util.ArrayList;

import spgui.SPDialog;
import spgui.SPWindow;
import spgui.componenet.Button;
import spgui.componenet.ToggleBtn;
import spgui.componenet.UploadArea;
import spgui.componenet.Snackbar.Direct;

public class GUI {
    SPWindow window = new SPWindow(800, 600);

    Border bottom = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black);
    Border empty = new EmptyBorder(0, 2, 5, 2);
    Border border = new CompoundBorder(bottom, empty);

    private int page = 0;
    private boolean check_ownerName;
    private boolean check_ownerID;
    private boolean check_certificateID;
    private boolean check_Issuer;
    private boolean check_vaild_period = true;

    private JTextField ownerName;
    private JTextField ownerID;
    private JTextField certificateID;
    private JTextField Issuer;
    private JTextField vaild_period;

    private JLabel ownerName_errText;
    private JLabel ownerID_errText;
    private JLabel certificateID_errText;
    private JLabel Issuer_errText;
    private JLabel vaild_period_errText;
    private JLabel hasVaildPeriod_txt;
    private JLabel table;
    private JLabel upload_txt_label;
    private JLabel upload_certificate_btn;
    private JLabel upload_json_btn;
    private JLabel add_new_data_btn;
    private JLabel upload_check_txt;
    private JLabel left_arrow_btn;
    private JLabel right_arrow_btn;

    private ToggleBtn hasVaildPeriod;
    private Button dialog_yes_btn;
    private Button dialog_no_btn;
    private Button upload_btn;

    private SPDialog load_json_dialog;
    private SPDialog add_new_data_dialog;
    private SPDialog upload_certificate_dialog;
    private UploadCertification upload;
    private UploadArea uploadArea;
    private ReadJson readJson = new ReadJson();
    private String path;
    private JSONArray dataArray;
    private JSONObject dataObject;

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
    private ImageIcon left_arrow = createImageIcon("/upload_window/res/left_arrow.png", null);
    private ImageIcon left_arrow_big = createImageIcon("/upload_window/res/left_arrow_big.png", null);
    private ImageIcon right_arrow = createImageIcon("/upload_window/res/right_arrow.png", null);
    private ImageIcon right_arrow_big = createImageIcon("/upload_window/res/right_arrow_big.png", null);
    // private ImageIcon upload_btn_icon = new
    // ImageIcon(getClass().getResource("/upload_window/res/bao.png"));

    private ArrayList<String> certification = new ArrayList<>();
    private DataTable dataTable = new DataTable(window);
    JSONObject bao = new JSONObject(
            "{\"certificateID\":\"#123456789\",\"issuer\":\"NTCU\",\"ownerID\":\"R124760286\",\"ownerName\":\"BigGrayBao\",\"vaildPeriod\":\"1999/02/11\"}");

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

        dataTable.setBounds(50, 160, 800, 600);
        window.addi(dataTable, Integer.valueOf(300));

        // Text
        upload_txt_label = new JLabel("", upload_txt, JLabel.CENTER);
        upload_txt_label.setBounds(0, -30, 800, 600);
        upload_txt_label.setVisible(true);
        window.addi(upload_txt_label);

        // Upload certificate
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

                upload_check_txt = new JLabel("<html><body align='center'>上傳後即無法更改<br>確定要上傳嗎?</body></html>", null,
                        JLabel.CENTER);
                upload_check_txt.setFont(new Font("標楷體", Font.BOLD, 30));

                // upload_check_txt.setFont(new Font("jf open 粉圓 1.0", Font.BOLD, 28));
                upload_check_txt.setBounds(0, 0, 350, 120);

                // Upload certificate dialog
                upload_certificate_dialog = new SPDialog(350, 200);
                upload_certificate_dialog.setLocationRelativeTo(window);
                upload_certificate_dialog.setTitle("資料上鏈");
                upload_certificate_dialog.setTitleBarColor(new Color(10, 10, 10, 250));
                upload_certificate_dialog.setBackgroundColor(new Color(120, 120, 120, 255));

                dialog_no_btn = new Button(100, 40, 24, null, new Color(255, 255, 255, 255), "取消", 48, true);
                dialog_no_btn.setFont(new Font("Microsoft Tai Le", Font.BOLD, 18));
                dialog_no_btn.setBounds(45, 110, 140, 70);
                dialog_no_btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        upload_certificate_dialog.setVisible(false);
                    }
                });
                dialog_yes_btn = new Button(100, 40, 24, null, new Color(255, 255, 255, 255), "確定", 48, true);
                dialog_yes_btn.setFont(new Font("Microsoft Tai Le", Font.BOLD, 18));
                dialog_yes_btn.setBounds(205, 110, 140, 70);
                dialog_yes_btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        upload_certificate_dialog.setVisible(false);
                        dataTable.getData().forEach(item -> {
                            System.out.println(item);
                        });
                        dataTable.data.clear();
                        dataTable.update();
                    }
                });

                // System.out.println(dataTable.getData());

                upload_certificate_dialog.addi(dialog_no_btn);
                upload_certificate_dialog.addi(dialog_yes_btn);
                upload_certificate_dialog.addi(upload_check_txt);
                upload_certificate_dialog.setVisible(true);
            }
        });
        window.addi(upload_certificate_btn, 200);

        // Add new data
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

                add_new_data_dialog = new SPDialog(600, 450);
                add_new_data_dialog.setLocationRelativeTo(window);
                add_new_data_dialog.setTitle("新增資料");
                add_new_data_dialog.setTitleBarColor(new Color(10, 10, 10, 250));
                add_new_data_dialog.setBackgroundColor(new Color(120, 120, 120, 255));
                hasVaildPeriod = new ToggleBtn(50, 30);
                hasVaildPeriod.setBounds(180, 205, 200, 150);
                hasVaildPeriod.setColor(new Color(255, 44, 140, 200), new Color(255, 44, 140, 255),
                        new Color(255, 44, 140, 100));

                isFocusable(ownerName);
                isFocusable(ownerID);
                isFocusable(certificateID);
                isFocusable(Issuer);
                check_vaild_period = true;
                // ownerName_errText.setVisible(false);
                // ownerID_errText.setVisible(false);
                // certificateID_errText.setVisible(false);
                // Issuer_errText.setVisible(false);
                // vaild_period_errText.setVisible(false);

                dialog_yes_btn = new Button(120, 50, 32, null, new Color(255, 255, 255, 255), "新增", 48, true);
                dialog_yes_btn.setFont(new Font("Microsoft Tai Le", Font.BOLD, 18));
                dialog_yes_btn.setBounds(370, 340, 140, 70);
                dialog_yes_btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        ownerName.setFocusable(false);
                        ownerID.setFocusable(false);
                        certificateID.setFocusable(false);
                        Issuer.setFocusable(false);
                        vaild_period.setFocusable(false);

                        // InputControl check = new InputControl();
                        if (!(check_ownerName && check_ownerID && check_certificateID && check_Issuer
                                && check_vaild_period)) {
                            System.out.println("input error");
                            vaild_period.setFocusable(true);
                            window.snackbar.show("請檢查資料格式是否錯誤", 4000, Direct.Bottom);
                        } else {
                            add_new_data_dialog.setVisible(false);

                            dataObject = new JSONObject();
                            dataObject.put("ownerName", ownerName.getText());
                            dataObject.put("ownerID", ownerID.getText());
                            dataObject.put("certificateID", certificateID.getText());
                            dataObject.put("issuer", Issuer.getText());
                            dataObject.put("vaildPeriod", vaild_period.getText());
                            dataTable.addData(dataObject);

                            ownerName.setText("");
                            ownerName.addFocusListener(new JTextFieldHintListener(ownerName, "擁有者名稱"));
                            ownerID.setText("");
                            ownerID.addFocusListener(new JTextFieldHintListener(ownerID, "身分證"));
                            certificateID.setText("");
                            certificateID.addFocusListener(new JTextFieldHintListener(certificateID, "證書編號"));
                            Issuer.setText("");
                            Issuer.addFocusListener(new JTextFieldHintListener(Issuer, "發證單位"));
                            vaild_period.setText("none");
                            vaild_period.setEditable(false);
                            vaild_period.setFocusable(false);
                        }

                    }
                });
                dialog_no_btn = new Button(120, 50, 32, null, new Color(255, 255, 255, 255), "取消", 48, true);
                dialog_no_btn.setFont(new Font("Microsoft Tai Le", Font.BOLD, 18));
                dialog_no_btn.setBounds(110, 340, 140, 70);
                dialog_no_btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        add_new_data_dialog.setVisible(false);
                        ownerName.setFocusable(false);
                        ownerID.setFocusable(false);
                        certificateID.setFocusable(false);
                        Issuer.setFocusable(false);
                        vaild_period.setFocusable(false);
                    }
                });
                hasVaildPeriod.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        boolean status = hasVaildPeriod.getStatus();
                        if (status) {
                            vaild_period.setFocusable(false);
                            vaild_period.setEditable(false);
                            vaild_period.setText("none");
                            vaild_period.setForeground(new Color(191, 191, 191));
                        } else {
                            vaild_period.setFocusable(true);
                            vaild_period.setEditable(true);
                            vaild_period.addFocusListener(new JTextFieldHintListener(vaild_period, "有效期限"));
                        }
                        ownerName.setFocusable(false);
                        ownerID.setFocusable(false);
                        certificateID.setFocusable(false);
                        Issuer.setFocusable(false);
                    }
                });
                add_new_data_dialog.addi(ownerName);
                add_new_data_dialog.addi(ownerName_errText);
                add_new_data_dialog.addi(ownerID);
                add_new_data_dialog.addi(ownerID_errText);
                add_new_data_dialog.addi(certificateID);
                add_new_data_dialog.addi(certificateID_errText);
                add_new_data_dialog.addi(Issuer);
                add_new_data_dialog.addi(Issuer_errText);
                add_new_data_dialog.addi(vaild_period);
                add_new_data_dialog.addi(vaild_period_errText);
                add_new_data_dialog.addi(dialog_yes_btn);
                add_new_data_dialog.addi(dialog_no_btn);
                add_new_data_dialog.addi(hasVaildPeriod);
                add_new_data_dialog.addi(hasVaildPeriod_txt);
                dialog_yes_btn.setVisible(true);
                dialog_no_btn.setVisible(true);
                add_new_data_dialog.setVisible(true);
            }
        });
        window.addi(add_new_data_btn);

        // Upload file
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
                // Upload json dialog
                load_json_dialog = new SPDialog(600, 450);
                load_json_dialog.setLocationRelativeTo(window);
                load_json_dialog.setTitle("檔案上傳");
                load_json_dialog.setTitleBarColor(new Color(10, 10, 10, 250));
                load_json_dialog.setBackgroundColor(new Color(120, 120, 120, 255));

                /****************************
                 * A uploadArea to add file *
                 ****************************/
                uploadArea = new UploadArea(250, 150, 54, 28, 24);
                uploadArea.setBounds(180, 100, 450, 450);

                dialog_no_btn = new Button(120, 50, 32, null, new Color(255, 255, 255, 255), "取消", 48, true);
                dialog_no_btn.setFont(new Font("Microsoft Tai Le", Font.BOLD, 18));
                dialog_no_btn.setBounds(110, 340, 140, 70);
                dialog_no_btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        load_json_dialog.setVisible(false);
                    }
                });
                dialog_yes_btn = new Button(120, 50, 32, null, new Color(255, 255, 255, 255), "新增", 48, true);
                dialog_yes_btn.setFont(new Font("Microsoft Tai Le", Font.BOLD, 18));
                dialog_yes_btn.setBounds(370, 340, 140, 70);
                dialog_yes_btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (readJson.ReadJson(uploadArea.getPath()).equals("err"))
                            window.snackbar.show("請上傳json檔", 4000, Direct.Bottom);
                        else {
                            dataArray = new JSONArray(readJson.ReadJson(uploadArea.getPath()));
                            dataTable.addData(dataArray);
                            // System.out.println(dataArray);
                            load_json_dialog.setVisible(false);
                        }
                    }
                });

                load_json_dialog.addi(uploadArea);
                load_json_dialog.addi(dialog_no_btn);
                load_json_dialog.addi(dialog_yes_btn);
                dialog_no_btn.setVisible(true);
                dialog_yes_btn.setVisible(true);
                load_json_dialog.setVisible(true);
            }
        });
        window.addi(upload_json_btn);

        left_arrow_btn = new JLabel("", left_arrow, JLabel.CENTER);
        left_arrow_btn.setBounds(640, 507, 33, 30);
        left_arrow_btn.setVisible(true);
        left_arrow_btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                left_arrow_btn.setIcon(left_arrow_big);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                left_arrow_btn.setIcon(left_arrow);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                left_arrow_btn.setIcon(left_arrow);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                left_arrow_btn.setIcon(left_arrow_big);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                page--;
                page = dataTable.changPage(page);
            }
        });
        window.addi(left_arrow_btn, Integer.valueOf(500));

        right_arrow_btn = new JLabel("", right_arrow, JLabel.CENTER);
        right_arrow_btn.setBounds(710, 507, 33, 30);
        right_arrow_btn.setVisible(true);
        right_arrow_btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                right_arrow_btn.setIcon(right_arrow_big);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                right_arrow_btn.setIcon(right_arrow);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                right_arrow_btn.setIcon(right_arrow);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                right_arrow_btn.setIcon(right_arrow_big);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                page++;
                page = dataTable.changPage(page);
            }
        });
        window.addi(right_arrow_btn, Integer.valueOf(500));

        /***************************
         * TextField for ownerName *
         ***************************/
        ownerName = new JTextField();
        ownerName.setBounds(70, 50, 200, 50);
        ownerName.setFont(new Font("標楷體", Font.BOLD, 30));
        ownerName.addFocusListener(new JTextFieldHintListener(ownerName, "擁有者名稱"));
        ownerName.setOpaque(false);
        ownerName.setBorder(border);
        ownerName.enableInputMethods(false);
        ownerName.setFocusable(false);
        ownerName.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent event) {
                if (ownerName.getText().matches("[\\S]{1,10}")) {
                    ownerName_errText.setVisible(false);
                    check_ownerName = true;
                } else {
                    ownerName_errText.setVisible(true);
                    check_ownerName = false;
                }
            }
        });
        ownerName_errText = new JLabel("名稱不能為空");
        ownerName_errText.setForeground(Color.RED);
        ownerName_errText.setFont(new Font("標楷體", Font.PLAIN, 16));
        ownerName_errText.setBounds(70, 105, 200, 20);

        /*************************
         * TextField for ownerID *
         *************************/
        ownerID = new JTextField();
        ownerID.setBounds(70, 130, 200, 50);
        ownerID.setFont(new Font("標楷體", Font.BOLD, 30));
        ownerID.addFocusListener(new JTextFieldHintListener(ownerID, "身分證"));
        ownerID.setOpaque(false);
        ownerID.setBorder(border);
        ownerID.enableInputMethods(false);
        ownerID.setFocusable(false);
        ownerID.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent event) {
                if (ownerID.getText().matches("^[A-Z]{1}[1-2]{1}[0-9]{8}$")) {
                    ownerID_errText.setVisible(false);
                    check_ownerID = true;
                } else {
                    ownerID_errText.setVisible(true);
                    check_ownerID = false;
                }
            }
        });
        ownerID_errText = new JLabel("格式錯誤 請確認格式");
        ownerID_errText.setForeground(Color.RED);
        ownerID_errText.setFont(new Font("標楷體", Font.PLAIN, 16));
        ownerID_errText.setBounds(70, 185, 200, 20);

        /*******************************
         * TextField for certificateID *
         *******************************/
        certificateID = new JTextField();
        certificateID.setBounds(330, 50, 200, 50);
        certificateID.setFont(new Font("標楷體", Font.BOLD, 30));
        certificateID.addFocusListener(new JTextFieldHintListener(certificateID, "證書編號"));
        certificateID.setOpaque(false);
        certificateID.setBorder(border);
        certificateID.enableInputMethods(false);
        certificateID.setFocusable(false);
        certificateID.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent event) {
                if (certificateID.getText().matches("^#{1}[0-9]{9}$")) {
                    certificateID_errText.setVisible(false);
                    check_certificateID = true;
                } else {
                    certificateID_errText.setVisible(true);
                    check_certificateID = false;
                }
            }
        });
        certificateID_errText = new JLabel("格式錯誤 請確認格式");
        certificateID_errText.setForeground(Color.RED);
        certificateID_errText.setFont(new Font("標楷體", Font.PLAIN, 16));
        certificateID_errText.setBounds(330, 105, 200, 20);

        /************************
         * TextField for Issuer *
         ************************/
        Issuer = new JTextField();
        Issuer.setBounds(330, 130, 200, 50);
        Issuer.setFont(new Font("標楷體", Font.BOLD, 30));
        Issuer.addFocusListener(new JTextFieldHintListener(Issuer, "發證單位"));
        Issuer.setOpaque(false);
        Issuer.setBorder(border);
        Issuer.enableInputMethods(false);
        Issuer.setFocusable(false);
        Issuer.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent event) {
                if (Issuer.getText().matches("[\\S]{1,10}")) {
                    Issuer_errText.setVisible(false);
                    check_Issuer = true;
                } else {
                    Issuer_errText.setVisible(true);
                    check_Issuer = false;
                }
            }
        });
        Issuer_errText = new JLabel("發證單位不能為空");
        Issuer_errText.setForeground(Color.RED);
        Issuer_errText.setFont(new Font("標楷體", Font.PLAIN, 16));
        Issuer_errText.setBounds(330, 185, 200, 20);

        /******************************
         * TextField for Vaild period *
         ******************************/
        vaild_period = new JTextField();
        vaild_period.setBounds(330, 210, 200, 50);
        vaild_period.setFont(new Font("標楷體", Font.BOLD, 30));
        vaild_period.setText("none");
        vaild_period.setForeground(new Color(191, 191, 191));
        vaild_period.setOpaque(false);
        vaild_period.setBorder(border);
        vaild_period.enableInputMethods(false);
        vaild_period.setFocusable(false);
        vaild_period.setEditable(false);
        vaild_period.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent event) {
                if (vaild_period.getText().matches("^[1-9]{1}[0-9]{3}/[0-1]{1}[0-9]{1}/[0-3]{1}[0-9]{1}$")
                        || vaild_period.getText().equals("none")) {
                    vaild_period_errText.setVisible(false);
                    check_vaild_period = true;
                } else {
                    vaild_period_errText.setVisible(true);
                    check_vaild_period = false;
                }
            }
        });
        vaild_period_errText = new JLabel("日期格式為(yyyy/mm/dd)");
        vaild_period_errText.setForeground(Color.RED);
        vaild_period_errText.setFont(new Font("標楷體", Font.PLAIN, 16));
        vaild_period_errText.setBounds(330, 265, 200, 20);
        vaild_period_errText.setVisible(false);

        hasVaildPeriod_txt = new JLabel("有無期限", null, JLabel.CENTER);
        hasVaildPeriod_txt.setBounds(25, 210, 200, 50);
        hasVaildPeriod_txt.setFont(new Font("標楷體", Font.BOLD, 24));
        hasVaildPeriod_txt.setForeground(Color.WHITE);

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

    protected void isFocusable(JTextField textField) {
        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                textField.setFocusable(true);
            }
        });
    }

    // class MyTextField extends JComponent {

    // private static final long serialVersionUID = 1L;
    // public JTextField textField;
    // public JLabel label;

    // public MyTextField() {

    // }

    // public void init(int width, int textField_height, int label_height) {
    // textField = new JTextField();
    // textField.setBounds(0, 0, width, textField_height);
    // textField.setFont(new Font("標楷體", Font.BOLD, 30));
    // textField.setOpaque(false);
    // textField.setBorder(border);
    // textField.enableInputMethods(false);
    // add(textField);

    // label = new JLabel("1231");
    // label.setForeground(Color.RED);
    // label.setBounds(0, textField_height, width, label_height);
    // label.setFont(new Font("標楷體", Font.PLAIN, 16));
    // add(label);
    // }

    // public String getText() {
    // return textField.getText();
    // }

    // public void setText(String s) {
    // textField.setText(s);
    // }
    // }
}
