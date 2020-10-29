package upload_window;

public class InputControl {

    public InputControl() {

    }

    public boolean checkInput(String ownerName, String ownerID, String certificateID, String issuer,
            String vaildPeriod) {
        boolean check = false;

        check = checkOwnerName(ownerName) && checkOwnerID(ownerID) && checkCertificateID(certificateID)
                && checkIssuer(issuer) && checkVaildPeriod(vaildPeriod);

        return check;
    }

    public boolean checkOwnerName(String ownerName) {
        return true;
    }

    public boolean checkOwnerID(String ownerID) {
        return ownerID.matches("^[A-Z]{1}[1-2]{1}[0-9]{8}$");
    }

    public boolean checkCertificateID(String certificateID) {
        return certificateID.matches("^#{1}[0-9]{9}$");
    }

    public boolean checkIssuer(String issuer) {
        return true;
    }

    public boolean checkVaildPeriod(String vaildPeriod) {
        return vaildPeriod.equals("none") ? true
                : vaildPeriod.matches("^[1-9]{1}[0-9]{3}/[0-1]{1}[0-9]{1}/[0-3]{1}[0-9]{1}$");
    }
}
