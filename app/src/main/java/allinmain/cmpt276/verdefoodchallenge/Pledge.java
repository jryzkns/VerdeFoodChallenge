package allinmain.cmpt276.verdefoodchallenge;

public class Pledge {
    private String id;
    private String userid;
    private Float pledgeAmount;

    Pledge(){}

    public Pledge(String id, String userid, Float pledgeAmount) {
        this.id = id;
        this.userid = userid;
        this.pledgeAmount = pledgeAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Float getPledgeAmount() {
        return pledgeAmount;
    }

    public void setPledgeAmount(Float pledgeAmount) {
        this.pledgeAmount = pledgeAmount;
    }
}
