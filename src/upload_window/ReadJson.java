package upload_window;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadJson {

    public String ReadJson(String path) {
        String s = "", temp = null;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));// 讀取原始json檔案

            while ((temp = br.readLine()) != null) {
                s += temp;
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return s;
    }
}
