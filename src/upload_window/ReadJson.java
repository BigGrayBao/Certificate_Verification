package upload_window;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadJson {

    public String ReadJson(ArrayList<String> path) {
        String s = "", temp = null, tempPath;
        if (path.isEmpty())
            System.err.println("No path");
        if (!path.get(0).substring(path.get(0).length() - 4, path.get(0).length()).equals("json"))
            return "err";
        for (int i = 0; i < path.size(); i++) {
            tempPath = path.get(i);
            System.out.println(tempPath);
            try {
                BufferedReader br = new BufferedReader(new FileReader(path.get(0)));// 讀取原始json檔案

                while ((temp = br.readLine()) != null) {
                    s += temp;
                }
                br.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return s;
    }
}
