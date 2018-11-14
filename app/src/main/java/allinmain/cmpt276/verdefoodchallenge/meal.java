package allinmain.cmpt276.verdefoodchallenge;

public class meal {
    String mName,mProtein,mrName,mrLoc,mDesc;
//    something for image here

    public meal(String mName, String mProtein, String mrName, String mrLoc, String mDesc) {
        this.mName = mName;
        this.mProtein = mProtein;
        this.mrName = mrName;
        this.mrLoc = mrLoc;
        this.mDesc = mDesc;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmProtein() {
        return mProtein;
    }

    public void setmProtein(String mProtein) {
        this.mProtein = mProtein;
    }

    public String getMrName() {
        return mrName;
    }

    public void setMrName(String mrName) {
        this.mrName = mrName;
    }

    public String getMrLoc() {
        return mrLoc;
    }

    public void setMrLoc(String mrLoc) {
        this.mrLoc = mrLoc;
    }

    public String getmDesc() {
        return mDesc;
    }

    public void setmDesc(String mDesc) {
        this.mDesc = mDesc;
    }
}