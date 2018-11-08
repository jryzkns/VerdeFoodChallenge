package allinmain.cmpt276.verdefoodchallenge;

public class UserInformation {
    public String NAME;
    //public String lastinital;
    public String LOCATION;
    public String ID;
    public String PLEDGEAMOUNT;

    public UserInformation(){

    }

    public UserInformation(String NAME, String LOCATION, String ID, String PLEDGEAMOUNT) {
        this.NAME = NAME;
        this.LOCATION = LOCATION;
        this.ID = ID;
        this.PLEDGEAMOUNT = PLEDGEAMOUNT;
    }
}
