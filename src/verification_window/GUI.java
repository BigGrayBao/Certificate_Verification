package verification_window;

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
    SPWindow window = new SPWindow(900, 600);

    Border bottom = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black);
    Border empty = new EmptyBorder(0, 2, 5, 2);
    Border border = new CompoundBorder(bottom, empty);

    private int page = 0;
    private String pagestr;

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
    private JLabel uploadArea_txt;
    private JLabel add_new_data_btn;
    private JLabel upload_check_txt;
    private JLabel left_arrow_btn;
    private JLabel right_arrow_btn;
    private JLabel page_txt;

    private ToggleBtn hasVaildPeriod;
    private Button dialog_yes_btn;
    private Button dialog_no_btn;
    private Button upload_btn;

    private SPDialog load_json_dialog;
    private SPDialog add_new_data_dialog;
    private SPDialog upload_certificate_dialog;
    private UploadArea uploadArea;
    private String path;
    private JSONArray dataArray;
    private JSONObject dataObject;

    private ImageIcon tittlebar_icon = createImageIcon("/verification_window/res/BigGrayBao.png", null);
    private ImageIcon table_icon = createImageIcon("/verification_window/res/table.png", null);
    private ImageIcon verification_txt = createImageIcon("/verification_window/res/verification_txt.png", null);
    private ImageIcon upload_json = createImageIcon("/verification_window/res/upload_json.png", null);
    private ImageIcon upload_json_big = createImageIcon("/verification_window/res/upload_json_entered.png", null);
    private ImageIcon add_new_data = createImageIcon("/verification_window/res/add_new_data.png", null);
    private ImageIcon add_new_data_big = createImageIcon("/verification_window/res/add_new_data_entered.png", null);
    private ImageIcon left_arrow = createImageIcon("/verification_window/res/left_arrow.png", null);
    private ImageIcon left_arrow_big = createImageIcon("/verification_window/res/left_arrow_big.png", null);
    private ImageIcon right_arrow = createImageIcon("/verification_window/res/right_arrow.png", null);
    private ImageIcon right_arrow_big = createImageIcon("/verification_window/res/right_arrow_big.png", null);

    private ImageIcon upload_certificate = createImageIcon("/upload_window/res/upload_certificate.png", null);
    private ImageIcon upload_certificate_big = createImageIcon("/upload_window/res/upload_certificate_entered.png",
            null);
    // private ImageIcon upload_btn_icon = new
    // ImageIcon(getClass().getResource("/upload_window/res/bao.png"));

    private ArrayList<String> certification = new ArrayList<>();
    private ReadJson readJson = new ReadJson();
    private DataTable dataTable = new DataTable(window);
    JSONObject bao = new JSONObject(
            "{\"certificateID\":\"#123456789\",\"issuer\":\"NTCU\",\"ownerID\":\"R124760286\",\"ownerName\":\"BigGrayBao\",\"vaildPeriod\":\"1999/02/11\"}");
    String certificate_info = "{\"certificateID\":\"#123456789\",\"issuer\":\"NTCU\",\"ownerID\":\"R124760286\",\"ownerName\":\"BigGrayBao\",\"vaildPeriod\":\"1999/02/11\",\"dateIssue\":\"1999/02/11\"}";

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
        table.setBounds(0, -30, 900, 600);
        table.setVisible(true);
        window.addi(table, 200);

        dataTable.setBounds(50, 160, 900, 600);
        window.addi(dataTable, Integer.valueOf(300));

        // Text
        upload_txt_label = new JLabel("", verification_txt, JLabel.CENTER);
        upload_txt_label.setBounds(0, -30, 800, 600);
        upload_txt_label.setVisible(true);
        window.addi(upload_txt_label);

        // Add new data
        add_new_data_btn = new JLabel("", add_new_data, JLabel.CENTER);
        add_new_data_btn.setBounds(530, 73, 180, 50);
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

                add_new_data_dialog = new SPDialog(350, 200);
                add_new_data_dialog.setLocationRelativeTo(window);
                add_new_data_dialog.setTitle("資料驗證");
                add_new_data_dialog.setTitleBarColor(new Color(10, 10, 10, 250));
                add_new_data_dialog.setBackgroundColor(new Color(120, 120, 120, 255));

                isFocusable(certificateID);

                dialog_yes_btn = new Button(100, 40, 24, null, new Color(255, 255, 255, 255), "驗證", 48, true);
                dialog_yes_btn.setFont(new Font("Microsoft Tai Le", Font.BOLD, 18));
                dialog_yes_btn.setBounds(205, 110, 140, 70);
                dialog_yes_btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        certificateID.setFocusable(false);
                        certificateID.setForeground(Color.WHITE);

                        if (!check_certificateID) {
                            // System.out.println("input error");
                            window.snackbar.show("請檢查資料格式是否錯誤", 4000, Direct.Bottom);
                        } else {
                            // 證書編號在這拿
                            // certificateID.getText();

                            certificateID.setText("");
                            certificateID.addFocusListener(new JTextFieldHintListener(certificateID, "證書編號"));

                            add_new_data_dialog.setVisible(false);

                            // 回傳的Obj放這
                            dataObject = new JSONObject(certificate_info);
                            dataTable.addData(dataObject);
                        }
                    }
                });
                dialog_no_btn = new Button(100, 40, 24, null, new Color(255, 255, 255, 255), "取消", 48, true);
                dialog_no_btn.setFont(new Font("Microsoft Tai Le", Font.BOLD, 18));
                dialog_no_btn.setBounds(45, 110, 140, 70);
                dialog_no_btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        add_new_data_dialog.setVisible(false);
                        certificateID.setFocusable(false);
                        certificateID.setText("");
                        certificateID.addFocusListener(new JTextFieldHintListener(certificateID, "證書編號"));
                    }
                });
                add_new_data_dialog.addi(certificateID);
                add_new_data_dialog.addi(certificateID_errText);
                add_new_data_dialog.addi(dialog_yes_btn);
                add_new_data_dialog.addi(dialog_no_btn);
                dialog_yes_btn.setVisible(true);
                dialog_no_btn.setVisible(true);
                add_new_data_dialog.setVisible(true);
            }
        });
        window.addi(add_new_data_btn);

        // Upload file
        upload_json_btn = new JLabel("", upload_json, JLabel.CENTER);
        upload_json_btn.setBounds(700, 75, 180, 50);
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
                uploadArea = new UploadArea(450, 230, 60, 36, 70);
                uploadArea.setBounds(78, 85, 450, 450);

                uploadArea_txt = new JLabel("請點擊此處或將檔案拖曳至此", null, JLabel.CENTER);
                uploadArea_txt.setFont(new Font("華康中特圓體(P)", Font.PLAIN, 30));
                uploadArea_txt.setBounds(0, 30, 600, 35);
                uploadArea_txt.setForeground(Color.WHITE);

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
                            load_json_dialog.setVisible(false);

                            dataArray = new JSONArray(readJson.ReadJson(uploadArea.getPath()));
                            dataObject = new JSONObject();
                            for (int i = 0; i < dataArray.length(); i++) {
                                dataArray.getJSONObject(i).getString("certificateID");
                                // dataTable.addData(bao);
                                // System.out.println(dataArray);
                            }
                            dataTable.addData(dataArray);
                        }
                    }
                });

                load_json_dialog.addi(uploadArea_txt);
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
        left_arrow_btn.setBounds(740, 507, 33, 30);
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
                pagestr = Integer.toString(page + 1);
                page_txt.setText("第 " + pagestr + " 頁");
            }
        });
        window.addi(left_arrow_btn, Integer.valueOf(500));

        right_arrow_btn = new JLabel("", right_arrow, JLabel.CENTER);
        right_arrow_btn.setBounds(810, 507, 33, 30);
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
                pagestr = Integer.toString(page + 1);
                page_txt.setText("第 " + pagestr + " 頁");
            }
        });
        window.addi(right_arrow_btn, Integer.valueOf(500));

        /*******************************
         * TextField for certificateID *
         *******************************/
        certificateID = new JTextField();
        certificateID.setBounds(75, 30, 200, 50);
        certificateID.setFont(new Font("華康中特圓體(P)", Font.PLAIN, 24));
        certificateID.addFocusListener(new JTextFieldHintListener(certificateID, "輸入證書編號"));
        certificateID.setForeground(Color.WHITE);
        certificateID.setHorizontalAlignment(JTextField.CENTER);
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
        certificateID_errText.setBounds(100, 85, 200, 20);

        page_txt = new JLabel("第 1 頁");
        page_txt.setFont(new Font("華康中特圓體(P)", Font.PLAIN, 18));
        page_txt.setForeground(Color.WHITE);
        page_txt.setBounds(620, 505, 100, 30);
        page_txt.setHorizontalAlignment(JLabel.CENTER);
        window.addi(page_txt, Integer.valueOf(500));

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

}
