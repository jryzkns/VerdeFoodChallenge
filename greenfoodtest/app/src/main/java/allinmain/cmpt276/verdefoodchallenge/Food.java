package allinmain.cmpt276.verdefoodchallenge;

public class Food {
    private String name="";
    private int resid=0;
    private float co2=0f;
    private String info="";
   public  Food(String name,int resid,float co2,String info)
    {
       this.name=name;
       this.resid=resid;
       this.co2=co2;
       this.info=info;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public String getName()
    {
        return name;
    }
    public void setResid(int resid)
    {
        this.resid=resid;
    }
    public int getResid()
    {
        return resid;
    }
    public void setCo2(float co2)
    {
        this.co2=co2;
    }
    public float getCo2()
    {
        return co2;
    }
    public void setInfo(String info)
    {
        this.info=info;
    }
    public String getInfo()
    {
        return info;
    }
}
