package allinmain.cmpt276.verdefoodchallenge;

public class UserInformation {
    private String NAME;
    private String LOCATION;
    private String ID;
    private String PLEDGEAMOUNT;

    public UserInformation(){

    }

    public UserInformation(String NAME, String LOCATION, String ID, String PLEDGEAMOUNT) {
        this.NAME = NAME;
        this.LOCATION = LOCATION;
        this.ID = ID;
        this.PLEDGEAMOUNT = PLEDGEAMOUNT;
    }

    public String getNAME() {
        return NAME;
    }
    public String getLOCATION() {
        return LOCATION;
    }
    public String getID() {
        return ID;
    }
    public String getPLEDGEAMOUNT() {
        return PLEDGEAMOUNT;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }
    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public void setPLEDGEAMOUNT(String PLEDGEAMOUNT) {
        this.PLEDGEAMOUNT = PLEDGEAMOUNT;
    }
}