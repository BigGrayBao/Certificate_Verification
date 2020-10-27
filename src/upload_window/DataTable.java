package upload_window;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.awt.event.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.util.ArrayList;

public class DataTable extends JComponent {

    private static final long serialVersionUID = 1L;

    ArrayList<JSONObject> data = new ArrayList<>();

    public DataTable() {

    }

    public void addData(JSONObject jsObj) {
        data.add(jsObj);
        update();
    }

    public void addData(JSONArray jsArr) {
        for (int i = 0; i < jsArr.length(); i++)
            data.add(jsArr.getJSONObject(i));
        update();
    }

    public void update() {
        this.removeAll();
        int length = (data.size() >= 8) ? 8 : data.size();
        for (int i = 0; i < length; i++) {
            DataTableItem item = new DataTableItem(data.get(i));
            item.setBounds(0, this.getComponentCount() * 42, 800, 50);
            add(item);
        }
        this.repaint();
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
    private JLabel edit = new JLabel(edit_img);
    private JLabel delete = new JLabel(delete_img);

    public DataTableItem(JSONObject data_info) {
        ownerName.setForeground(Color.WHITE);
        ownerName.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 18));
        ownerName.setText(data_info.getString("ownerName"));
        ownerName.setBounds(15, 0, 200, 50);
        add(ownerName);

        ownerID.setForeground(Color.WHITE);
        ownerID.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 18));
        ownerID.setText(data_info.getString("ownerID"));
        ownerID.setBounds(138, 0, 200, 50);
        add(ownerID);

        certificateID.setForeground(Color.WHITE);
        certificateID.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 18));
        certificateID.setText(data_info.getString("certificateID"));
        certificateID.setBounds(260, 0, 200, 50);
        add(certificateID);

        issuer.setForeground(Color.WHITE);
        issuer.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 18));
        issuer.setText(data_info.getString("issuer"));
        issuer.setBounds(405, 0, 200, 50);
        add(issuer);

        vaildPeriod.setForeground(Color.WHITE);
        vaildPeriod.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 18));
        vaildPeriod.setText(data_info.getString("vaildPeriod"));
        vaildPeriod.setBounds(543, 0, 200, 50);
        add(vaildPeriod);

        edit.setBounds(655, 14, 18, 18);
        edit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                edit.setIcon(new ImageIcon(getClass().getResource("/upload_window/res/edit_big.png")));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                edit.setIcon(new ImageIcon(getClass().getResource("/upload_window/res/edit.png")));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                edit.setIcon(new ImageIcon(getClass().getResource("/upload_window/res/edit.png")));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                edit.setIcon(new ImageIcon(getClass().getResource("/upload_window/res/edit_big.png")));
            }

            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        add(edit);

        delete.setBounds(690, 14, 18, 18);
        delete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                delete.setIcon(new ImageIcon(getClass().getResource("/upload_window/res/delete_big.png")));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                delete.setIcon(new ImageIcon(getClass().getResource("/upload_window/res/delete.png")));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                delete.setIcon(new ImageIcon(getClass().getResource("/upload_window/res/delete.png")));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                delete.setIcon(new ImageIcon(getClass().getResource("/upload_window/res/delete_big.png")));
            }

            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        add(delete);
    }
}
