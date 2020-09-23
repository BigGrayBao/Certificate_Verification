package upload_window;

import java.util.ArrayList;

public class UploadCertification {

    private ArrayList<String> certification = new ArrayList<>();

    public void setCertification(ArrayList<String> certification) {
        this.certification = certification;
    }

    public ArrayList<String> getCertification() {
        return certification;
    }

    public void printCertification() {
        for (String s : certification) {
            System.out.println(s);
        }
    }

}
