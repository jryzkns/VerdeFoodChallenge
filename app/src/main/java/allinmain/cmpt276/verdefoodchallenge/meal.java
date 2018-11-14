package allinmain.cmpt276.verdefoodchallenge;

import android.graphics.Bitmap;

public class meal {
    String mName,mProtein,mrName,mrLoc,mDesc;

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