package upload_window;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import java.awt.event.*;

import org.json.JSONArray;
import org.json.JSONObject;

import spgui.SPDialog;
import spgui.SPWindow;
import spgui.componenet.ToggleBtn;
import spgui.componenet.Snackbar.Direct;
import spgui.componenet.Button;

import java.awt.*;
import java.util.ArrayList;

public class DataTable extends JComponent {

    private static final long serialVersionUID = 1L;
    private SPDialog add_new_data_dialog;
    private int page = 0;
    private boolean check_ownerName = true;
    private boolean check_ownerID = true;
    private boolean check_certificateID = true;
    private boolean check_Issuer = true;
    private boolean check_dateIssue = true;
    private boolean check_vaild_period = true;

    private JTextField ownerName;
    private JTextField ownerID;
    private JTextField certificateID;
    private JTextField Issuer;
    private JTextField dateIssue;
    private JTextField vaild_period;

    private JLabel hasVaildPeriod_txt;
    private JLabel ownerName_errText;
    private JLabel ownerID_errText;
    private JLabel certificateID_errText;
    private JLabel Issuer_errText;
    private JLabel dateIssue_errText;
    private JLabel vaild_period_errText;

    private ToggleBtn hasVaildPeriod;

    public Button dialog_yes_btn = new Button(120, 50, 32, null, new Color(255, 255, 255, 255), "修改", 48, true);;
    private Button dialog_no_btn;

    Border bottom = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black);
    Border empty = new EmptyBorder(0, 2, 5, 2);
    Border border = new CompoundBorder(bottom, empty);

    ArrayList<JSONObject> data = new ArrayList<>();
    SPWindow window;

    public DataTable(SPWindow window) {
        this.window = window;
    }

    public void addData(JSONObject jsObj) {
        data.add(0, jsObj);
        update();
    }

    public void addData(JSONArray jsArr) {
        for (int i = 0; i < jsArr.length(); i++)
            data.add(jsArr.getJSONObject(i));
        update();
    }

    public JComponent getTable() {
        return this;
    }

    public void update() {
        this.removeAll();
        int length = (data.size() >= 8) ? 8 : data.size();
        for (int i = 0; i < length; i++) {
            DataTableItem item = new DataTableItem(data.get(i));
            JSONObject myItem = data.get(i);
            item.setBounds(0, this.getComponentCount() * 42, 900, 50);
            item.delete.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    data.remove(myItem);
                    update();
                }
            });
            item.edit.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    check_ownerName = true;
                    check_ownerID = true;
                    check_certificateID = true;
                    check_Issuer = true;
                    check_dateIssue = true;
                    check_vaild_period = true;

                    /***************************
                     * TextField for ownerName *
                     ***************************/
                    ownerName = new JTextField();
                    ownerName.setText(myItem.getString("ownerName"));
                    ownerName.setForeground(Color.white);
                    ownerName.setBounds(70, 50, 200, 50);
                    ownerName.setFont(new Font("華康中特圓體(P)", Font.PLAIN, 24));
                    // ownerName.addFocusListener(new JTextFieldHintListener(ownerName, "擁有者名稱"));
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
                    ownerName_errText.setFont(new Font("華康中特圓體(P)", Font.PLAIN, 16));
                    ownerName_errText.setBounds(70, 105, 200, 20);
                    ownerName_errText.setVisible(false);

                    /*************************
                     * TextField for ownerID *
                     *************************/
                    ownerID = new JTextField();
                    ownerID.setText(myItem.getString("ownerID"));
                    ownerID.setForeground(Color.white);
                    ownerID.setBounds(70, 130, 200, 50);
                    ownerID.setFont(new Font("華康中特圓體(P)", Font.PLAIN, 24));
                    // ownerID.addFocusListener(new JTextFieldHintListener(ownerID, "身分證"));
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
                    ownerID_errText.setFont(new Font("華康中特圓體(P)", Font.PLAIN, 16));
                    ownerID_errText.setBounds(70, 185, 200, 20);
                    ownerID_errText.setVisible(false);

                    /*******************************
                     * TextField for certificateID *
                     *******************************/
                    certificateID = new JTextField();
                    certificateID.setText(myItem.getString("certificateID"));
                    certificateID.setForeground(Color.white);
                    certificateID.setBounds(330, 50, 200, 50);
                    certificateID.setFont(new Font("華康中特圓體(P)", Font.PLAIN, 24));
                    // certificateID.addFocusListener(new JTextFieldHintListener(certificateID,
                    // "證書編號"));
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
                    certificateID_errText.setFont(new Font("華康中特圓體(P)", Font.PLAIN, 16));
                    certificateID_errText.setBounds(330, 105, 200, 20);
                    certificateID_errText.setVisible(false);

                    /************************
                     * TextField for Issuer *
                     ************************/
                    Issuer = new JTextField();
                    Issuer.setText(myItem.getString("issuer"));
                    Issuer.setForeground(Color.white);
                    Issuer.setBounds(330, 130, 200, 50);
                    Issuer.setFont(new Font("華康中特圓體(P)", Font.PLAIN, 24));
                    // Issuer.addFocusListener(new JTextFieldHintListener(Issuer, "發證單位"));
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
                    Issuer_errText.setFont(new Font("華康中特圓體(P)", Font.PLAIN, 16));
                    Issuer_errText.setBounds(330, 185, 200, 20);
                    Issuer_errText.setVisible(false);

                    /***************************
                     * TextField for dateIssue *
                     ***************************/
                    dateIssue = new JTextField();
                    dateIssue.setText(myItem.getString("dateIssue"));
                    dateIssue.setForeground(Color.white);
                    dateIssue.setBounds(70, 210, 200, 50);
                    dateIssue.setFont(new Font("華康中特圓體(P)", Font.PLAIN, 24));
                    // dateIssue.addFocusListener(new JTextFieldHintListener(dateIssue, "發證日期"));
                    dateIssue.setOpaque(false);
                    dateIssue.setBorder(border);
                    dateIssue.enableInputMethods(false);
                    dateIssue.setFocusable(false);
                    dateIssue.addCaretListener(new CaretListener() {
                        public void caretUpdate(CaretEvent event) {
                            if (dateIssue.getText().matches("^[1-9]{1}[0-9]{3}/[0-1]{1}[0-9]{1}/[0-3]{1}[0-9]{1}$")) {
                                dateIssue_errText.setVisible(false);
                                check_dateIssue = true;
                            } else {
                                dateIssue_errText.setVisible(true);
                                check_dateIssue = false;
                            }
                        }
                    });
                    dateIssue_errText = new JLabel("發證日期不能為空");
                    dateIssue_errText.setForeground(Color.RED);
                    dateIssue_errText.setFont(new Font("華康中特圓體(P)", Font.PLAIN, 16));
                    dateIssue_errText.setBounds(70, 265, 200, 20);
                    dateIssue_errText.setVisible(false);

                    /******************************
                     * TextField for Vaild period *
                     ******************************/
                    vaild_period = new JTextField();
                    vaild_period.setText(myItem.getString("vaildPeriod"));
                    vaild_period.setForeground(Color.white);
                    vaild_period.setBounds(330, 290, 200, 50);
                    vaild_period.setFont(new Font("華康中特圓體(P)", Font.PLAIN, 24));
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
                    vaild_period_errText.setFont(new Font("華康中特圓體(P)", Font.PLAIN, 16));
                    vaild_period_errText.setBounds(330, 345, 200, 20);
                    vaild_period_errText.setVisible(false);

                    hasVaildPeriod_txt = new JLabel("有無期限", null, JLabel.CENTER);
                    hasVaildPeriod_txt.setBounds(25, 290, 200, 50);
                    hasVaildPeriod_txt.setFont(new Font("華康中特圓體(P)", Font.PLAIN, 24));
                    hasVaildPeriod_txt.setForeground(Color.WHITE);

                    add_new_data_dialog = new SPDialog(600, 500);
                    add_new_data_dialog.setLocationRelativeTo(null);
                    add_new_data_dialog.setTitle("修改資料");
                    add_new_data_dialog.setTitleBarColor(new Color(10, 10, 10, 250));
                    add_new_data_dialog.setBackgroundColor(new Color(120, 120, 120, 255));

                    hasVaildPeriod = new ToggleBtn(50, 30);
                    hasVaildPeriod.setBounds(180, 285, 200, 150);
                    hasVaildPeriod.setColor(new Color(255, 44, 140, 200), new Color(255, 44, 140, 255),
                            new Color(255, 44, 140, 100));

                    isFocusable(ownerName);
                    isFocusable(ownerID);
                    isFocusable(certificateID);
                    isFocusable(Issuer);
                    isFocusable(dateIssue);

                    dialog_yes_btn = new Button(120, 50, 32, null, new Color(255, 255, 255, 255), "修改", 48, true);
                    dialog_yes_btn.setFont(new Font("標楷體", Font.PLAIN, 18));
                    dialog_yes_btn.setBounds(370, 380, 140, 70);
                    dialog_yes_btn.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (!(check_ownerName && check_ownerID && check_certificateID && check_Issuer
                                    && check_dateIssue && check_vaild_period)) {
                                System.out.println("input error");
                                vaild_period.setFocusable(true);
                                window.snackbar.show("請檢查資料格式是否錯誤", 4000, Direct.Bottom);
                            } else {

                                add_new_data_dialog.setVisible(false);
                                ownerName.setFocusable(false);
                                ownerID.setFocusable(false);
                                certificateID.setFocusable(false);
                                Issuer.setFocusable(false);
                                dateIssue.setFocusable(false);
                                vaild_period.setFocusable(false);

                                myItem.put("ownerName", ownerName.getText());
                                myItem.put("ownerID", ownerID.getText());
                                myItem.put("certificateID", certificateID.getText());
                                myItem.put("issuer", Issuer.getText());
                                myItem.put("dateIssue", dateIssue.getText());
                                myItem.put("vaildPeriod", vaild_period.getText());
                                item.setDataView(myItem);

                                ownerName.setText("");
                                ownerName.addFocusListener(new JTextFieldHintListener(ownerName, "擁有者名稱"));
                                ownerID.setText("");
                                ownerID.addFocusListener(new JTextFieldHintListener(ownerID, "身分證"));
                                certificateID.setText("");
                                certificateID.addFocusListener(new JTextFieldHintListener(certificateID, "證書編號"));
                                Issuer.setText("");
                                Issuer.addFocusListener(new JTextFieldHintListener(Issuer, "發證單位"));
                                dateIssue.setText("");
                                dateIssue.addFocusListener(new JTextFieldHintListener(dateIssue, "發證日期"));
                                vaild_period.setText("none");
                                vaild_period.setEditable(false);
                                vaild_period.setFocusable(false);
                            }
                        }
                    });
                    dialog_no_btn = new Button(120, 50, 32, null, new Color(255, 255, 255, 255), "取消", 48, true);
                    dialog_no_btn.setFont(new Font("標楷體", Font.PLAIN, 18));
                    dialog_no_btn.setBounds(110, 380, 140, 70);
                    dialog_no_btn.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            add_new_data_dialog.setVisible(false);
                            ownerName.setFocusable(false);
                            ownerID.setFocusable(false);
                            certificateID.setFocusable(false);
                            Issuer.setFocusable(false);
                            dateIssue.setFocusable(false);
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
                            dateIssue.setFocusable(false);
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
                    add_new_data_dialog.addi(dateIssue);
                    add_new_data_dialog.addi(dateIssue_errText);
                    add_new_data_dialog.addi(vaild_period);
                    add_new_data_dialog.addi(vaild_period_errText);
                    add_new_data_dialog.addi(dialog_yes_btn);
                    add_new_data_dialog.addi(dialog_no_btn);
                    add_new_data_dialog.addi(hasVaildPeriod);
                    add_new_data_dialog.addi(hasVaildPeriod_txt);
                    dialog_yes_btn.setVisible(true);
                    dialog_no_btn.setVisible(true);
                    add_new_data_dialog.setVisible(true);

                    // System.out.println(data);
                }
            });
            add(item);
        }
        this.repaint();
    }

    public int changPage(int page) {
        if (data.isEmpty())
            return this.page;
        this.removeAll();
        this.page = page <= 0 || data.isEmpty() ? 0 : page;
        this.page = this.page >= (data.size() / 8) ? (data.size() / 8) : this.page;
        this.page = data.size() % 8 == 0 ? this.page - 1 : this.page;
        int temp = 8 * this.page;
        for (int i = temp; i < temp + 8 && i < data.size(); i++) {
            JSONObject myItem = data.get(i);
            DataTableItem item = new DataTableItem(data.get(i));
            item.setBounds(0, this.getComponentCount() * 42, 900, 50);
            item.delete.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    data.remove(myItem);
                    changPage(getPage());
                }
            });
            item.edit.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    check_ownerName = true;
                    check_ownerID = true;
                    check_certificateID = true;
                    check_Issuer = true;
                    check_dateIssue = true;
                    check_vaild_period = true;

                    /***************************
                     * TextField for ownerName *
                     ***************************/
                    ownerName = new JTextField();
                    ownerName.setText(myItem.getString("ownerName"));
                    ownerName.setForeground(Color.white);
                    ownerName.setBounds(70, 50, 200, 50);
                    ownerName.setFont(new Font("華康中特圓體(P)", Font.PLAIN, 24));
                    // ownerName.addFocusListener(new JTextFieldHintListener(ownerName, "擁有者名稱"));
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
                    ownerName_errText.setFont(new Font("華康中特圓體(P)", Font.PLAIN, 16));
                    ownerName_errText.setBounds(70, 105, 200, 20);
                    ownerName_errText.setVisible(false);

                    /*************************
                     * TextField for ownerID *
                     *************************/
                    ownerID = new JTextField();
                    ownerID.setText(myItem.getString("ownerID"));
                    ownerID.setForeground(Color.white);
                    ownerID.setBounds(70, 130, 200, 50);
                    ownerID.setFont(new Font("華康中特圓體(P)", Font.PLAIN, 24));
                    // ownerID.addFocusListener(new JTextFieldHintListener(ownerID, "身分證"));
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
                    ownerID_errText.setFont(new Font("華康中特圓體(P)", Font.PLAIN, 16));
                    ownerID_errText.setBounds(70, 185, 200, 20);
                    ownerID_errText.setVisible(false);

                    /*******************************
                     * TextField for certificateID *
                     *******************************/
                    certificateID = new JTextField();
                    certificateID.setForeground(Color.white);
                    certificateID.setText(myItem.getString("certificateID"));
                    certificateID.setBounds(330, 50, 200, 50);
                    certificateID.setFont(new Font("華康中特圓體(P)", Font.PLAIN, 24));
                    // certificateID.addFocusListener(new JTextFieldHintListener(certificateID,
                    // "證書編號"));
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
                    certificateID_errText.setFont(new Font("華康中特圓體(P)", Font.PLAIN, 16));
                    certificateID_errText.setBounds(330, 105, 200, 20);
                    certificateID_errText.setVisible(false);

                    /************************
                     * TextField for Issuer *
                     ************************/
                    Issuer = new JTextField();
                    Issuer.setText(myItem.getString("issuer"));
                    Issuer.setForeground(Color.white);
                    Issuer.setBounds(330, 130, 200, 50);
                    Issuer.setFont(new Font("華康中特圓體(P)", Font.PLAIN, 24));
                    // Issuer.addFocusListener(new JTextFieldHintListener(Issuer, "發證單位"));
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
                    Issuer_errText.setFont(new Font("華康中特圓體(P)", Font.PLAIN, 16));
                    Issuer_errText.setBounds(330, 185, 200, 20);
                    Issuer_errText.setVisible(false);

                    /***************************
                     * TextField for dateIssue *
                     ***************************/
                    dateIssue = new JTextField();
                    dateIssue.setText(myItem.getString("dateIssue"));
                    dateIssue.setForeground(Color.white);
                    dateIssue.setBounds(70, 210, 200, 50);
                    dateIssue.setFont(new Font("華康中特圓體(P)", Font.PLAIN, 24));
                    // dateIssue.addFocusListener(new JTextFieldHintListener(dateIssue, "發證日期"));
                    dateIssue.setOpaque(false);
                    dateIssue.setBorder(border);
                    dateIssue.enableInputMethods(false);
                    dateIssue.setFocusable(false);
                    dateIssue.addCaretListener(new CaretListener() {
                        public void caretUpdate(CaretEvent event) {
                            if (dateIssue.getText().matches("^[1-9]{1}[0-9]{3}/[0-1]{1}[0-9]{1}/[0-3]{1}[0-9]{1}$")) {
                                dateIssue_errText.setVisible(false);
                                check_dateIssue = true;
                            } else {
                                dateIssue_errText.setVisible(true);
                                check_dateIssue = false;
                            }
                        }
                    });
                    dateIssue_errText = new JLabel("發證日期不能為空");
                    dateIssue_errText.setForeground(Color.RED);
                    dateIssue_errText.setFont(new Font("華康中特圓體(P)", Font.PLAIN, 16));
                    dateIssue_errText.setBounds(70, 265, 200, 20);
                    dateIssue_errText.setVisible(false);

                    /******************************
                     * TextField for Vaild period *
                     ******************************/
                    vaild_period = new JTextField();
                    vaild_period.setText(myItem.getString("vaildPeriod"));
                    vaild_period.setForeground(Color.white);
                    vaild_period.setBounds(330, 290, 200, 50);
                    vaild_period.setFont(new Font("華康中特圓體(P)", Font.PLAIN, 24));
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
                    vaild_period_errText.setFont(new Font("華康中特圓體(P)", Font.PLAIN, 16));
                    vaild_period_errText.setBounds(330, 345, 200, 20);
                    vaild_period_errText.setVisible(false);

                    hasVaildPeriod_txt = new JLabel("有無期限", null, JLabel.CENTER);
                    hasVaildPeriod_txt.setBounds(25, 290, 200, 50);
                    hasVaildPeriod_txt.setFont(new Font("華康中特圓體(P)", Font.PLAIN, 24));
                    hasVaildPeriod_txt.setForeground(Color.WHITE);

                    add_new_data_dialog = new SPDialog(600, 500);
                    add_new_data_dialog.setLocationRelativeTo(null);
                    add_new_data_dialog.setTitle("修改資料");
                    add_new_data_dialog.setTitleBarColor(new Color(10, 10, 10, 250));
                    add_new_data_dialog.setBackgroundColor(new Color(120, 120, 120, 255));

                    hasVaildPeriod = new ToggleBtn(50, 30);
                    hasVaildPeriod.setBounds(180, 285, 200, 150);
                    hasVaildPeriod.setColor(new Color(255, 44, 140, 200), new Color(255, 44, 140, 255),
                            new Color(255, 44, 140, 100));

                    isFocusable(ownerName);
                    isFocusable(ownerID);
                    isFocusable(certificateID);
                    isFocusable(Issuer);
                    isFocusable(dateIssue);

                    dialog_yes_btn = new Button(120, 50, 32, null, new Color(255, 255, 255, 255), "修改", 48, true);
                    dialog_yes_btn.setFont(new Font("標楷體", Font.PLAIN, 18));
                    dialog_yes_btn.setBounds(370, 380, 140, 70);
                    dialog_yes_btn.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (!(check_ownerName && check_ownerID && check_certificateID && check_Issuer
                                    && check_dateIssue && check_vaild_period)) {
                                System.out.println("input error");
                                vaild_period.setFocusable(true);
                                window.snackbar.show("請檢查資料格式是否錯誤", 4000, Direct.Bottom);
                            } else {

                                add_new_data_dialog.setVisible(false);
                                ownerName.setFocusable(false);
                                ownerID.setFocusable(false);
                                certificateID.setFocusable(false);
                                Issuer.setFocusable(false);
                                dateIssue.setFocusable(false);
                                vaild_period.setFocusable(false);

                                myItem.put("ownerName", ownerName.getText());
                                myItem.put("ownerID", ownerID.getText());
                                myItem.put("certificateID", certificateID.getText());
                                myItem.put("issuer", Issuer.getText());
                                myItem.put("dateIssue", dateIssue.getText());
                                myItem.put("vaildPeriod", vaild_period.getText());
                                item.setDataView(myItem);

                                ownerName.setText("");
                                ownerName.addFocusListener(new JTextFieldHintListener(ownerName, "擁有者名稱"));
                                ownerID.setText("");
                                ownerID.addFocusListener(new JTextFieldHintListener(ownerID, "身分證"));
                                certificateID.setText("");
                                certificateID.addFocusListener(new JTextFieldHintListener(certificateID, "證書編號"));
                                Issuer.setText("");
                                Issuer.addFocusListener(new JTextFieldHintListener(Issuer, "發證單位"));
                                dateIssue.setText("");
                                dateIssue.addFocusListener(new JTextFieldHintListener(Issuer, "發證日期"));
                                vaild_period.setText("none");
                                vaild_period.setEditable(false);
                                vaild_period.setFocusable(false);
                            }
                        }
                    });
                    dialog_no_btn = new Button(120, 50, 32, null, new Color(255, 255, 255, 255), "取消", 48, true);
                    dialog_no_btn.setFont(new Font("標楷體", Font.PLAIN, 18));
                    dialog_no_btn.setBounds(110, 380, 140, 70);
                    dialog_no_btn.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            add_new_data_dialog.setVisible(false);
                            ownerName.setFocusable(false);
                            ownerID.setFocusable(false);
                            certificateID.setFocusable(false);
                            Issuer.setFocusable(false);
                            dateIssue.setFocusable(false);
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
                            dateIssue.setFocusable(false);
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
                    add_new_data_dialog.addi(dateIssue);
                    add_new_data_dialog.addi(dateIssue_errText);
                    add_new_data_dialog.addi(vaild_period);
                    add_new_data_dialog.addi(vaild_period_errText);
                    add_new_data_dialog.addi(dialog_yes_btn);
                    add_new_data_dialog.addi(dialog_no_btn);
                    add_new_data_dialog.addi(hasVaildPeriod);
                    add_new_data_dialog.addi(hasVaildPeriod_txt);
                    dialog_yes_btn.setVisible(true);
                    dialog_no_btn.setVisible(true);
                    add_new_data_dialog.setVisible(true);

                    // System.out.println(data);
                }
            });
            add(item);
        }
        this.repaint();
        return this.page;
    }

    protected void isFocusable(JTextField textField) {
        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                textField.setFocusable(true);
            }
        });
    }

    public int getPage() {
        System.out.println(page);
        return this.page;
    }

    public ArrayList<JSONObject> getData() {
        return this.data;
    }
}

class DataTableItem extends JComponent {

    private static final long serialVersionUID = 1L;

    private ImageIcon edit_img = new ImageIcon(getClass().getResource("/upload_window/res/edit.png"));
    private ImageIcon edit_big_img = new ImageIcon(getClass().getResource("/upload_window/res/edit_big.png"));
    private ImageIcon delete_img = new ImageIcon(getClass().getResource("/upload_window/res/delete.png"));
    private ImageIcon delete_big_img = new ImageIcon(getClass().getResource("/upload_window/res/delete_big.png"));

    private JLabel ownerName = new JLabel();
    private JLabel ownerID = new JLabel();
    private JLabel certificateID = new JLabel();
    private JLabel issuer = new JLabel();
    private JLabel vaildPeriod = new JLabel();
    private JLabel dateIssue = new JLabel();
    public JLabel edit = new JLabel(edit_img);
    public JLabel delete = new JLabel(delete_img);

    public DataTableItem(JSONObject data_info) {
        ownerName.setForeground(Color.WHITE);
        ownerName.setFont(new Font("標楷體", Font.PLAIN, 18));
        ownerName.setText(data_info.getString("ownerName"));
        ownerName.setBounds(15, 0, 100, 50);
        add(ownerName);

        ownerID.setForeground(Color.WHITE);
        ownerID.setFont(new Font("標楷體", Font.PLAIN, 18));
        ownerID.setText(data_info.getString("ownerID"));
        ownerID.setBounds(133, 0, 100, 50);
        add(ownerID);

        certificateID.setForeground(Color.WHITE);
        certificateID.setFont(new Font("標楷體", Font.PLAIN, 18));
        certificateID.setText(data_info.getString("certificateID"));
        certificateID.setBounds(255, 0, 100, 50);
        add(certificateID);

        issuer.setForeground(Color.WHITE);
        issuer.setFont(new Font("標楷體", Font.PLAIN, 18));
        issuer.setText(data_info.getString("issuer"));
        issuer.setBounds(400, 0, 100, 50);
        add(issuer);

        dateIssue.setForeground(Color.WHITE);
        dateIssue.setFont(new Font("標楷體", Font.PLAIN, 18));
        dateIssue.setText(data_info.getString("dateIssue"));
        dateIssue.setBounds(535, 0, 100, 50);
        add(dateIssue);

        vaildPeriod.setForeground(Color.WHITE);
        vaildPeriod.setFont(new Font("標楷體", Font.PLAIN, 18));
        vaildPeriod.setText(data_info.getString("vaildPeriod"));
        vaildPeriod.setBounds(673, 0, 100, 50);
        add(vaildPeriod);

        edit.setBounds(770, 14, 18, 18);
        edit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                edit.setIcon(edit_big_img);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                edit.setIcon(edit_img);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                edit.setIcon(edit_img);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                edit.setIcon(edit_big_img);
            }

            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        add(edit);

        delete.setBounds(800, 14, 18, 18);
        delete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                delete.setIcon(delete_big_img);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                delete.setIcon(delete_img);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                delete.setIcon(delete_img);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                delete.setIcon(delete_big_img);
            }

            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        add(delete);
    }

    public void setDataView(JSONObject data_info) {
        ownerName.setText(data_info.getString("ownerName"));
        ownerID.setText(data_info.getString("ownerID"));
        certificateID.setText(data_info.getString("certificateID"));
        issuer.setText(data_info.getString("issuer"));
        dateIssue.setText(data_info.getString("dateIssue"));
        vaildPeriod.setText(data_info.getString("vaildPeriod"));
        repaint();
    }
}
