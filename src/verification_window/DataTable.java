package verification_window;

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
    private boolean check_vaild_period = true;

    private JTextField ownerName;
    private JTextField ownerID;
    private JTextField certificateID;
    private JTextField Issuer;
    private JTextField vaild_period;

    private JLabel hasVaildPeriod_txt;
    private JLabel ownerName_errText;
    private JLabel ownerID_errText;
    private JLabel certificateID_errText;
    private JLabel Issuer_errText;
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
    private JLabel dateIssue = new JLabel();
    private JLabel vaildPeriod = new JLabel();
    public JLabel edit = new JLabel(edit_img);
    public JLabel delete = new JLabel(delete_img);
    private JLabel found = new JLabel("已驗證");
    private JLabel notfound = new JLabel("驗證失敗");

    public DataTableItem(JSONObject data_info) {
        ownerName.setForeground(Color.WHITE);
        ownerName.setFont(new Font("標楷體", Font.PLAIN, 18));
        ownerName.setText(data_info.getString("ownerName"));
        ownerName.setBounds(0, 0, 100, 50);
        add(ownerName);

        ownerID.setForeground(Color.WHITE);
        ownerID.setFont(new Font("標楷體", Font.PLAIN, 18));
        ownerID.setText(data_info.getString("ownerID"));
        ownerID.setBounds(123, 0, 100, 50);
        add(ownerID);

        certificateID.setForeground(Color.WHITE);
        certificateID.setFont(new Font("標楷體", Font.PLAIN, 18));
        certificateID.setText(data_info.getString("certificateID"));
        certificateID.setBounds(245, 0, 100, 50);
        add(certificateID);

        issuer.setForeground(Color.WHITE);
        issuer.setFont(new Font("標楷體", Font.PLAIN, 18));
        issuer.setText(data_info.getString("issuer"));
        issuer.setBounds(390, 0, 100, 50);
        add(issuer);

        dateIssue.setForeground(Color.WHITE);
        dateIssue.setFont(new Font("標楷體", Font.PLAIN, 18));
        dateIssue.setText(data_info.getString("dateIssue"));
        dateIssue.setBounds(515, 0, 100, 50);
        add(dateIssue);

        vaildPeriod.setForeground(Color.WHITE);
        vaildPeriod.setFont(new Font("標楷體", Font.PLAIN, 18));
        vaildPeriod.setText(data_info.getString("vaildPeriod"));
        vaildPeriod.setBounds(653, 0, 100, 50);
        add(vaildPeriod);

        found.setForeground(Color.GREEN);
        found.setFont(new Font("華康中特圓體(P)", Font.PLAIN, 18));
        found.setBounds(755, -3, 200, 50);

        notfound.setForeground(Color.RED);
        notfound.setFont(new Font("華康中特圓體(P)", Font.PLAIN, 18));
        notfound.setBounds(745, -3, 200, 50);

        String check = data_info.getString("ownerName");
        if (check.equals("error"))
            add(notfound);
        else
            add(found);
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
