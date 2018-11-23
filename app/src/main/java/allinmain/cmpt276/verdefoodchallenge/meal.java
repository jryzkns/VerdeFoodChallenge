package allinmain.cmpt276.verdefoodchallenge;

public class meal {
    String mName,mProtein,mrName,mrLoc,mDesc,mImg;

    String mID, muID;

    public meal(String mName, String mProtein, String mrName, String mrLoc, String mDesc, String mImg) {
        this.mName = mName;
        this.mProtein = mProtein;
        this.mrName = mrName;
        this.mrLoc = mrLoc;
        this.mDesc = mDesc;
        this.mImg = mImg;
    }

    public meal(String mName, String mProtein, String mrName, String mrLoc, String mDesc, String mImg, String mID, String muID) {
        this.mName = mName;
        this.mProtein = mProtein;
        this.mrName = mrName;
        this.mrLoc = mrLoc;
        this.mDesc = mDesc;
        this.mImg = mImg;
        this.mID = mID;
        this.muID = muID;
    }

    public String getmImg() {
        return mImg;
    }

    public void setmImg(String mImg) {
        this.mImg = mImg;
    }

    public String getmName() { return mName; }

    public String getmProtein() {
        return mProtein;
    }

    public String getMrName() {
        return mrName;
    }

    public String getMrLoc() {
        return mrLoc;
    }

    public String getmDesc() {
        return mDesc;
    }

}