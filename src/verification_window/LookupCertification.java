package verification_window;

import org.json.JSONObject;

public class LookupCertification {

    private String ownerName, ownerID, certificateID, issuer, vaild_period;

    private JSONObject jsonobj;

    public boolean setCertificateInfo(String certificate_info) {
        boolean isfind = true;
        jsonobj = new JSONObject(certificate_info);

        ownerName = jsonobj.getString("ownerName");
        if (ownerName.equals("error"))
            isfind = false;
        ownerID = jsonobj.getString("ownerID");
        if (ownerID.equals("error"))
            isfind = false;
        certificateID = jsonobj.getString("certificateID");
        if (certificateID.equals("error"))
            isfind = false;
        issuer = jsonobj.getString("issuer");
        if (issuer.equals("error"))
            isfind = false;
        vaild_period = jsonobj.getString("vaildPeriod");
        if (vaild_period.equals("error"))
            isfind = false;

        // System.out.println(ownerName);
        // System.out.println(ownerID);
        // System.out.println(certificateID);
        // System.out.println(issuer);
        // System.out.println(vaild_period);

        return isfind;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public String getCertificateID() {
        return certificateID;
    }

    public String getIssuer() {
        return issuer;
    }

    public String getVaildPeriod() {
        return vaild_period;
    }
}
