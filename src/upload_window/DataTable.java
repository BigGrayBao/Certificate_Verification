package upload_window;

import javax.swing.JComponent;
import javax.swing.JLabel;

import org.json.JSONObject;

import java.awt.*;
import java.util.ArrayList;

public class DataTable extends JComponent {

    private static final long serialVersionUID = 1L;

    ArrayList<JSONObject> data = new ArrayList<>();

    public DataTable() {

    }

    public void addData(JSONObject jsArr) {
        data.add(jsArr);
        update();
    }

    public void update() {
        this.removeAll();
        int length = (data.size() >= 8) ? 8 : data.size();
        for (int i = 0; i < length; i++) {
            DataTableItem item = new DataTableItem(data.get(i));
            item.setBounds(0, this.getComponentCount() * 42, 600, 50);
            add(item);
        }
        this.repaint();
    }
}

class DataTableItem extends JComponent {

    private static final long serialVersionUID = 1L;

    private JLabel ownerName = new JLabel();
    private JLabel ownerID = new JLabel();
    private JLabel certificateID = new JLabel();
    private JLabel issuer = new JLabel();
    private JLabel vaildPeriod = new JLabel();

    public DataTableItem(JSONObject data_info) {
        ownerName.setForeground(Color.WHITE);
        ownerName.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 20));
        ownerName.setText(data_info.getString("ownerName"));
        ownerName.setBounds(10, 0, 200, 50);
        add(ownerName);

        ownerID.setForeground(Color.WHITE);
        ownerID.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 20));
        ownerID.setText(data_info.getString("ownerID"));
        ownerID.setBounds(110, 0, 200, 50);
        add(ownerID);

        certificateID.setForeground(Color.WHITE);
        certificateID.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 20));
        certificateID.setText(data_info.getString("certificateID"));
        certificateID.setBounds(210, 0, 200, 50);
        add(certificateID);

        issuer.setForeground(Color.WHITE);
        issuer.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 20));
        issuer.setText(data_info.getString("issuer"));
        issuer.setBounds(310, 0, 200, 50);
        add(issuer);

        vaildPeriod.setForeground(Color.WHITE);
        vaildPeriod.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 20));
        vaildPeriod.setText(data_info.getString("vaildPeriod"));
        vaildPeriod.setBounds(410, 0, 200, 50);
        add(vaildPeriod);
    }
}